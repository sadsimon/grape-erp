<template>
	<div class="parent-container">
		<GrStamp :textValue="'已过账'" :isShow="isfinish"/>
		<el-container class="layout-container">
			<el-card class="head layout-query">
				<el-form ref="saveRef" :inline="true" :rules="dataRules" :model="dataForm">
					<div style="font-size:20px; text-align:center;margin-bottom: 15px;">支出单</div>
					<el-form-item prop="documentCode" label="单据编号">
						<el-input readonly v-model="dataForm.documentCode"></el-input>
					</el-form-item>
					<el-form-item prop="documentTime" label="录单时间">
						<el-date-picker
						        v-model="dataForm.documentTime"
						        type="datetime"
								value-format="YYYY-MM-DD HH:mm:ss"
								:disabled="isfinish"
						      />
					</el-form-item>
					<el-form-item prop="contactunitsId" label="结算单位">
						<GrContactunitsInput width="250px" :disabled="isfinish" v-model="dataForm.contactunitsId"></GrContactunitsInput>
					</el-form-item>
					<el-form-item prop="userId" label="经手人">
						<MaUserInput :disabled="isfinish" v-model="dataForm.userId"></MaUserInput>
					</el-form-item>
					<el-form-item prop="remark" label="备注">
						<el-input
						    v-model="dataForm.remark"
						    style="width: 540px"
						  />
					</el-form-item>
				</el-form>
			</el-card>
			<el-card class="main">
				<div style="width: 100%; display: flex;">
					<div style="flex: 1;" />
					<GrFile :businessCode="dataForm.documentCode" />
				</div>
				<IncomeExpendList :amountType="amountType" :isfinish="isfinish" :height="listHeight" v-model:initialData="dataForm.documentDetailList"></IncomeExpendList>
			</el-card>
			<GrDocumentFoot @isArrowUp="isArrowUpFu" maxHeight="300" height="150">
				<el-tabs v-model="activeName" type="card">
					<el-tab-pane label="结算信息" name="payment">
						<el-form ref="dataFormRef" style="margin-top: 15px;" :model="dataForm" :rules="dataRules" label-width="100px">
							<el-row :gutter="5">
								<el-col v-for="(accountDetail) in dataForm.documentAccountDetailList" :span="24" :lg="8" :md="8" :sm="12">
									<el-form-item prop="number" label="付款信息">
										<el-input
										v-model="accountDetail.amount"
										      style="max-width: 300px"
										      placeholder="金额"
											  :key="accountDetail.key"
											  :disabled="isfinish"
										    >
										      <template #prepend>
													<GrSettlementAccountInput :disabled="isfinish" v-model="accountDetail.accountId" width="150px" placeholder="请选择账户"/>
										      </template>
											  <template #append>
												  <el-button :icon="Delete" :disabled="isfinish" @click.prevent="removeDomain(accountDetail)" />
											</template>
										    </el-input>
									</el-form-item>
								</el-col>
								<el-col :span="24" :lg="4" :md="4" :sm="12">
									<el-form-item>									
										<el-button text :disabled="isfinish" style="margin-left: -83px;" type="primary" @click="addDomain">添加账户</el-button>
									</el-form-item>
								</el-col>
							</el-row>
							
						</el-form>
					</el-tab-pane>
				</el-tabs>
			</GrDocumentFoot>
			<el-card>
				<div class="footer-containers" style="width: 100%; display: flex;">
					<div class="footer-left">本单应付:<el-text tag="b" class="footer-left-text" type="danger"
							size="large">{{ shouldAmount }}</el-text>
						<el-divider direction="vertical" />
						此前应付:<el-text tag="b" class="footer-left-text" type="danger" size="large">{{ historyPayAmount }}</el-text>
						<el-divider direction="vertical" />
						合计应付:<el-text tag="b" class="footer-left-text" type="danger"
							size="large">{{ totalAmount }}</el-text>
					</div>
					<div class="footer-right">
						<el-button size="large" v-if="!isfinish" type="primary" @click="submitHandle">保存</el-button>
						<el-button size="large" v-if="isfinish" type="primary" @click="addNext">新增</el-button>
						<el-button size="large" v-if="isfinish" type="primary" @click="copyNext">复制</el-button>
						<el-button size="large" @click="closeWindow">关闭</el-button>
					</div>
				</div>
			</el-card>
		</el-container>
	</div>
	<!-- 弹框挂载点 -->
	<div id="documentList-dialog-container"></div>
</template>

<script setup lang="ts" name="DocumentSettlementExpendIndex">
	import { useCrud } from '@/hooks'
	import { reactive, ref, onMounted, onUnmounted, computed, watch, watchEffect } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	import { useRouter, useRoute } from 'vue-router'
	import IncomeExpendList from '.././incomeExpendList/index.vue'
	import { ElMessage } from 'element-plus/es'
	import { useDocumentSubmitApi, useGetDocumentCodeApi, useGetHistoryPayAmountApi, useDocumentApi } from '@/api/product/order'
	import { DocumentDetail } from '@/views/document/index'
	import { useWindowResize } from '@/views/document/useWindowResize'
	import { SettleDetailInt, DocumentAccountDetailInt } from '@/views/document/settlement/settlement'
	import { Delete } from '@element-plus/icons-vue'
	import { getCurrentDate } from '@/utils/tool'
	import { cloneDeep } from 'lodash-es'
	import { closeTab } from '@/utils/tabs'

	const isfinish = ref(false)

	const saveRef = ref()
	
	const amountType = ref('2')
	const documentType = ref('36')
	
	interface DataForm {
		contactunitsId : number | null
		userId : number | null
		remark : string
		documentCode : string
		documentTime : string
		finalAmount : number | null
		documentStatus : string
		documentType: string
		documentDetailList : DocumentDetail[]
		documentSettleDetailList : SettleDetailInt[]
		documentAccountDetailList : DocumentAccountDetailInt[]
		documentAccountDetailListDelete : number[]
	}
	
	const initialDataForm = {
		contactunitsId: null,
		userId: null,
		remark: '',
		documentCode: '',
		documentTime: '',
		finalAmount: null,
		documentStatus: '2',
		documentType: documentType.value,
		amountType: amountType.value,
		documentDetailList: [{
			'id': null,
			'productId': null,
			'storeId': null,
			'productNumber': '',
			'productName': '',
			'barcode': '',
			'specs': '',
			'quantity': null,
			'unitId': '',
			'unitPrice': null,
			'amount': null,
			'projectId': null,
			'expectPurchasePrice': null,
			
		}],
		documentSettleDetailList: [{
			key: null,
			id: null,
			documentCode: '',
			settleDocumentCode: '',
			paymentAmount: null,
			amountType: amountType.value,
			advanceAmount: null,
			discountAmount: null
		}],
		documentAccountDetailList:[{
			key: Date.now(),
			id: null,
			accountId: null,
			accountCode: '',
			accountName: '',
			amount: null,
			amountType: amountType.value,
			remark: ''
		}],
		documentAccountDetailListDelete: []
	}
	
	const dataForm = ref<DataForm>(initialDataForm)
	
	const addDomain = () => {
		dataForm.value.documentAccountDetailList.push({
			key: Date.now(),
			id: null,
			accountId: null,
			accountCode: '',
			accountName: '',
			amount: null,
			amountType: amountType.value,
			remark: ''
		})
	}
	
	const removeDomain = (item: DocumentAccountDetailInt) => {
		const index = dataForm.value.documentAccountDetailList.indexOf(item)
		if (index !== -1) {
			dataForm.value.documentAccountDetailList.splice(index, 1)
			if (item.id) {
				dataForm.value.documentAccountDetailListDelete.push(item.id)
			}
		}
		//删除最后一个时只清空数据
		if(dataForm.value.documentAccountDetailList.length===0){
			addDomain()
		}
		
	}

	const addNext =() => {
		init(false)
	}
	
	const copyNext =() =>{
		getDocumentCode()
		dataForm.value.documentTime = getCurrentDate()
		isfinish.value = false
		getHistoryPayAmount(dataForm.value.contactunitsId)
		ElMessage.success({
			message: '复制成功'
		})
	}
	
	const route = useRoute()
	const router = useRouter();
	const closeWindow = () => {
	  closeTab(router, route)
	}
	
	const init = async (isUpdate: boolean, id?: number) => {
		if (isUpdate && id) {
			getDocument(id)
		}else{
			dataForm.value = cloneDeep(initialDataForm)
			getDocumentCode()
			dataForm.value.documentTime = getCurrentDate()
			historyPayAmount.value = 0
		}
		isfinish.value = false
	}
	
	const getDocument= (id: number) =>{
		useDocumentApi(id).then(res=>{
			dataForm.value = res.data
		})
	}
	
	const getDocumentCode = () => {
		useGetDocumentCodeApi(documentType.value).then(res => {
			dataForm.value.documentCode = res.data
		})
	}

	const activeName = ref('payment')
	
	//应收款总金额
	const finalAmount = computed(() =>{
		return dataForm.value.documentDetailList.reduce((sum, item) => {
			// 将 amount 转换为数字，如果是字符串
			const amount = Number(item.amount) || 0
			return sum + amount
		}, 0)
	})
	
	//本单应付款(采购单)=(应收金额-实际收款金额)
	const shouldAmount = computed(() => {
		return (finalAmount.value - (dataForm.value.documentAccountDetailList.reduce((sum, item) => {
			// 将 amount 转换为数字，如果是字符串
			const amount = Number(item.amount) || 0
			return sum + amount
		}, 0)))
	})
	
	//此前应付款
	const historyPayAmount = ref(0)
	watch(
		() => dataForm.value.contactunitsId,
		newVal => {
			if(newVal){
				getHistoryPayAmount(newVal)
			}
		}
	)
	
	const getHistoryPayAmount= (contactunitsId : number | null) =>{
		useGetHistoryPayAmountApi(contactunitsId).then(res => {
			historyPayAmount.value = res.data
		})
	}
	
	//合计应付款
	const totalAmount = computed(() => {
		return shouldAmount.value + historyPayAmount.value
	})

	const submitHandle = () => {
		saveRef.value.validate((valid : boolean) => {
			if (!valid) {
				return false
			}

			if (dataForm.value.documentDetailList.length === 0) {
				ElMessage.error({
					message: '入库商品不能为空'
				})
			}
			dataForm.value.finalAmount = finalAmount.value
			useDocumentSubmitApi(dataForm.value).then(() => {
				ElMessage.success({
					message: '操作成功',
					duration: 500
				})
				isfinish.value = true
			})
		})
	}

	const dataRules = ref({
		contactunitsId: [{ required: true, message: '必填项不能为空', trigger: 'change' }],
		documentTime: [{ required: true, message: '必填项不能为空', trigger: 'change' }]
	})
	
	const id = ref('');
	watchEffect(()=>{
		if(typeof route.query.id === 'string'){
			id.value = route.query.id
			init(true, Number(id.value))
		}
	})
	
	onMounted(()=>{
		init(false)
	})
	// 引入窗口高度逻辑
	const { headHeight, listHeight, footHeight, occupyHeight, getHeadHeight, updateTableHeight,isArrowUpFu } = useWindowResize(192)
</script>

<style scoped>
	.parent-container {
		height: calc(100vh - 82px);
		/* 确保父容器占满整个高度 */
	}

	.layout-container {
		display: flex;
		flex-direction: column;
		min-height: 100%;
		/* 确保 layout-container 至少占满父容器的高度 */
	}
	.head {
		margin-bottom: 5px;
	}
	.main {
		flex: 2;
		/* 主内容区域占据剩余空间 */
		/* overflow-y: auto; */
		/* 当主内容超出容器高度时启用滚动 */
		margin-bottom: 5px;
	}	
	.footer-left {
		flex: 1;
		line-height: 38px;
	}
	.footer-left-text {
		--el-text-font-size: var(--footer-left-text-size)
	}
</style>