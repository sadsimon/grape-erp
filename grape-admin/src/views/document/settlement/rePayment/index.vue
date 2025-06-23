<template>
	<div class="parent-container">
		<GrStamp :textValue="'已过账'" :isShow="isfinish"/>
		<el-container class="layout-container">
			<el-card class="head layout-query">
				<el-form ref="saveRef" :inline="true" :rules="dataRules" :model="dataForm">
					<div style="font-size:20px; text-align:center;margin-bottom: 15px;">预付款单</div>
					<el-row>
						<el-col :span="24" :lg="6" :md="6" :sm="6" :xs='6'>
							<el-form-item prop="documentCode" label="单据编号">
								<el-input readonly v-model="dataForm.documentCode"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="24" :lg="6" :md="6" :sm="6" :xs='6'>
							<el-form-item prop="documentTime" label="录单时间">
								<el-date-picker
										v-model="dataForm.documentTime"
										type="datetime"
										value-format="YYYY-MM-DD HH:mm:ss"
										:disabled="isfinish"
									  />
							</el-form-item>
						</el-col>
						<el-col :span="24" :lg="6" :md="6" :sm="6" :xs='6'>
							<el-form-item prop="contactunitsId" label="结算单位">
								<GrContactunitsInput width="250px" :disabled="isfinish" v-model="dataForm.contactunitsId"></GrContactunitsInput>
							</el-form-item>
						</el-col>
						<el-col :span="24" :lg="6" :md="6" :sm="6" :xs='6'>
							<el-form-item prop="userId" label="经手人">
								<MaUserInput :disabled="isfinish" v-model="dataForm.userId"></MaUserInput>
							</el-form-item>
						</el-col>
						<el-col :span="24" :lg="6" :md="6" :sm="6" :xs='6'>
							<el-form-item prop="remark" label="备注">
								<el-input
									v-model="dataForm.remark"
									style="width: 540px"
									:rows="1"
									type="textarea"
								  />
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</el-card>
			<el-card class="main">
				<AccountList :isfinish="isfinish" :height="listHeight" v-model:initialData="dataForm.documentAccountDetailList"></AccountList>
			</el-card>
			<el-card>
				<div class="footer-containers" style="width: 100%; display: flex;">
					<div class="footer-left">
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

<script setup lang="ts" name="DocumentSettlementPaymentIndex">
	import { useCrud } from '@/hooks'
	import { reactive, ref, onMounted, onUnmounted, computed, watch, watchEffect } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	import { useRouter, useRoute } from 'vue-router'
	import AccountList from '.././accountList/index.vue'
	import DocumentList from '.././documentList/index.vue'
	import { ElMessage } from 'element-plus/es'
	import { useDocumentSubmitApi, useGetDocumentCodeApi, useGetHistoryPayAmountApi, useDocumentApi } from '@/api/product/order'
	import { DocumentAccountDetailInt, documentDataInt } from '.././settlement'
	import { getCurrentDate } from '@/utils/tool'
	import { cloneDeep } from 'lodash-es'
	import { closeTab } from '@/utils/tabs'
	import { useWindowResize } from '@/views/document/useWindowResize'

	const isfinish = ref(false)
	const saveRef = ref()
	const amountType = ref('2')
	const documentType = ref('38')
	
	interface DataForm {
		contactunitsId : number | null
		userId : number | null
		remark : string
		documentCode : string
		documentTime : string
		finalAmount : number | null
		accountAmount : number | null
		documentStatus : string
		amountType: string
		documentType: string
		documentSettleDetailList : documentDataInt[]
		documentAccountDetailList : DocumentAccountDetailInt[]
	}

	const initialDataForm = {
		contactunitsId: null,
		userId: null,
		remark: '',
		documentCode: '',
		documentTime: getCurrentDate(),
		finalAmount: null,
		accountAmount :  null,
		documentStatus: '1',
		amountType: amountType.value,
		documentType: documentType.value,
		documentSettleDetailList: [{
			id: null,
			documentCode: '',
			documentType: '',
			documentTime: '',
			contactunitsName: '',
			contactunitsCode: '',
			realName: '',
			remark: '',
			finalAmount: null,
			pendingAmount: null,
			paymentAmount: null,
			advancePaymentAmount: null,
			discountAmount: null,
			sumAmount: null
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
		}]
	}
	
	const dataForm = ref<DataForm>(initialDataForm)
	
	const addNext =() => {
		init(false)
	}
	
	const copyNext =() =>{
		getDocumentCode()
		dataForm.value.documentTime = getCurrentDate()
		isfinish.value = false
		ElMessage.success({
			message: '复制成功'
		})
	}
	
	const init = async (isUpdate: boolean, id?: number) => {
		if (isUpdate && id) {
			getDocument(id)
		}else{
			dataForm.value = cloneDeep(initialDataForm)
			getDocumentCode()
			dataForm.value.documentTime = getCurrentDate()
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

	//总金额
	const totalPayAmount = computed(() => {
		return dataForm.value.documentAccountDetailList.reduce((sum, item) => {
			const amount = Number(item.amount) || 0
			return sum + amount
		}, 0)
	})
	
	const submitHandle = () => {
		saveRef.value.validate((valid : boolean) => {
			if (!valid) {
				return false
			}

			/* if (dataForm.value.documentSettleDetailList.length === 0) {
				ElMessage.error({
					message: '入库商品不能为空'
				})
			} */
			dataForm.value.finalAmount = 0
			useDocumentSubmitApi(dataForm.value).then(() => {
				ElMessage.success({
					message: '操作成功',
					duration: 500
				})
				isfinish.value = true
			})
		})
	}

	const route = useRoute()
	const router = useRouter();
	const closeWindow = () => {
	  closeTab(router, route)
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
	const { headHeight, listHeight, footHeight, occupyHeight, getHeadHeight, updateTableHeight,isArrowUpFu } = useWindowResize(160)
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