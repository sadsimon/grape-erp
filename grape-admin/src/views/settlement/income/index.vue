<template>
	<div class="parent-container">
		<GrStamp :textValue="'已过账'" :isShow="isfinish"/>
		<el-container class="layout-container">
			<el-card class="head layout-query">
				<el-form ref="saveRef" :inline="true" 	 :rules="dataRules" :model="dataForm">
					<div style="font-size:20px; text-align:center;margin-bottom: 15px;">收入单</div>
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
				</el-form>
			</el-card>
			<el-card class="main">
				<IncomeExpendList :amountType="amountType" :isfinish="isfinish" :height="listHeight" v-model:initialData="dataForm.documentDetailList"></IncomeExpendList>
			</el-card>
			<GrDocumentFoot @isArrowUp="isArrowUpFu" maxHeight="300" height="150">
				<el-tabs v-model="activeName" type="card">
					<el-tab-pane label="结算信息" name="payment">
						<el-form ref="dataFormRef" style="margin-top: 15px;" :model="dataForm" :rules="dataRules" label-width="100px">
							<el-row :gutter="5">
								<el-col v-for="(accountDetail) in dataForm.documentAccountDetailList" :span="24" :lg="8" :md="8" :sm="12">
									<el-form-item prop="number" label="收款信息">
										<el-input
										v-model="accountDetail.amount"
										      style="max-width: 300px"
										      placeholder="金额"
											  :key="accountDetail.key"
											  :disabled="isfinish"
										    >
										      <template #prepend>
													<GrSettlementAccountInput :disabled="isfinish" v-model="accountDetail.accountId" width="150px" placeholder="类型"/>
										      </template>
											  <template #append>
												  <el-button :icon="Delete" :disabled="isfinish" @click.prevent="removeDomain(accountDetail)" />
											</template>
										    </el-input>
									</el-form-item>
								</el-col>
								<el-col :span="24" :lg="8" :md="12" :sm="24">
									<el-button text :disabled="isfinish" style="margin-bottom: 5px;" type="primary" @click="addDomain">添加账户</el-button>
								</el-col>
							</el-row>
							
						</el-form>
					</el-tab-pane>
				</el-tabs>
			</GrDocumentFoot>
			<el-card>
				<div class="footer-containers" style="width: 100%; display: flex;">
					<div class="footer-left">本单应收:<el-text tag="b" class="footer-left-text" type="danger">{{ shouldAmount }}</el-text>
						<el-divider direction="vertical" />
						此前应收:<el-text tag="b" class="footer-left-text" type="danger" size="large">{{ historyReceivePayment }}</el-text>
						<el-divider direction="vertical" />
						合计应收:<el-text tag="b" class="footer-left-text" type="danger"
							size="large">{{ totalAmount }}</el-text>
					</div>
					<div class="footer-right">
						<div class="footer-right">
							<el-button size="large" v-if="!isfinish" type="primary" @click="submitHandle">保存</el-button>
							<el-button size="large" v-if="isfinish" type="primary" @click="addNext">新增</el-button>
							<el-button size="large" v-if="isfinish" type="primary" @click="copyNext">复制</el-button>
							<el-button size="large" v-if="isfinish" @click="closeWindow">关闭</el-button>
						</div>
					</div>
				</div>
			</el-card>
		</el-container>
	</div>
	<!-- 弹框挂载点 -->
	<div id="documentList-dialog-container"></div>
</template>

<script setup lang="ts" name="SettlementIncomeIndex">
	import { useCrud } from '@/hooks'
	import { reactive, ref, onMounted, onUnmounted, computed, watch, watchEffect } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	import { useRouter, useRoute } from 'vue-router'
	import IncomeExpendList from '.././incomeExpendList/index.vue'
	import { ElMessage } from 'element-plus/es'
	import { useDocumentSubmitApi, useGetDocumentCodeApi, useGetgetHistoryReceivePaymentApi, useDocumentApi } from '@/api/product/order'
	import { dataInt } from '@/views/purchase/purchase'
	import { SettleDetailInt, DocumentAccountDetailInt } from '@/views/settlement/settlement'
	import { Delete } from '@element-plus/icons-vue'
	import { getCurrentDate } from '@/utils/tool'
	import { cloneDeep } from 'lodash-es'
	import { closeTab } from '@/utils/tabs'
	
	const isfinish = ref(false)
	
	const saveRef = ref()
	
	const amountType = ref('1')
	const documentType = ref('37')
	
	interface DataForm {
		contactunitsId : number | null
		userId : number | null
		documentCode : string
		documentTime : string
		finalAmount : number | null
		documentStatus : string
		documentType: string
		documentDetailList : dataInt[]
		documentSettleDetailList : SettleDetailInt[]
		documentAccountDetailList : DocumentAccountDetailInt[]
		documentAccountDetailListDelete : number[]
	}
	
	const initialDataForm = {
		contactunitsId: null,
		userId: null,
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
			'number': '',
			'name': '',
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
		getHistoryReceivePayment(dataForm.value.contactunitsId)
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
			historyReceivePayment.value = 0
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
	
	//本单应收款(销售单)=(应收金额-实际收款金额)
	const shouldAmount = computed(() => {
		return (finalAmount.value - (dataForm.value.documentAccountDetailList.reduce((sum, item) => {
			// 将 amount 转换为数字，如果是字符串
			const amount = Number(item.amount) || 0
			return sum + amount
		}, 0)))
	})
	//此前应收款
	const historyReceivePayment = ref(0)
	watch(
		() => dataForm.value.contactunitsId,
		newVal => {
			if(newVal){
				getHistoryReceivePayment(newVal)
			}
		}
	)
	
	const getHistoryReceivePayment= (contactunitsId : number | null) =>{
		useGetgetHistoryReceivePaymentApi(contactunitsId).then(res => {
			historyReceivePayment.value = res.data
		})
	}
	
	//合计应收款
	const totalAmount = computed(() => {
		return shouldAmount.value + historyReceivePayment.value
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
	//====================================窗口高度部分start====================================
	const listHeight = ref(0)
	const footHeight = ref(0)
	const occupyHeight = ref(255)//其他部分占用高度
	const isArrowUpFu = (isArrowUp: number) => {
		footHeight.value = isArrowUp
		updateTableHeight()
	}
	
	// 更新表格高度的方法
	const updateTableHeight = () => {
		listHeight.value = window.innerHeight - footHeight.value - occupyHeight.value
	}
	
	// 在组件挂载时初始化高度，并监听窗口大小变化
	onMounted(() => {
		updateTableHeight() // 初始化高度
		window.addEventListener('resize', updateTableHeight) // 监听窗口大小变化
	})
	
	// 在组件卸载时移除事件监听
	onUnmounted(() => {
		window.removeEventListener('resize', updateTableHeight)
	})
	//====================================窗口高度部分end====================================
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
			margin-bottom: 5px;
	}	
	.footer-left {
		flex: 1;
		line-height: 30px;
	}
	.footer-left-text {
		--el-text-font-size: var(--footer-left-text-size)
	}
</style>