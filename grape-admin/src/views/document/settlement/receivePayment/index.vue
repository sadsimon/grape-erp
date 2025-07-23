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
					<GrFile :disabled="isfinish" :businessCode="dataForm.documentCode" />
				</div>
				<AccountList :isfinish="isfinish" :height="listHeight" v-model:initialData="dataForm.documentAccountDetailList"></AccountList>
				<div style="width: 100%; display: flex; justify-content: flex-end; margin-top: 4px;">
					<el-space>
						<el-form-item prop="advanceInt" label="使用预收款">
							<GrNumberInput
							v-model="dataForm.advanceInt"
							      style="max-width: 300px"
							      placeholder="金额"
								  :disabled="isfinish"
							    >
								  <template #append>
								  	  余额&nbsp;<span style="color: red;">{{ balance }}</span>
								  </template>
							    </GrNumberInput>
						</el-form-item>
					</el-space>
				</div>			
			</el-card>
			<GrDocumentFoot @isArrowUp="isArrowUpFu" :maxHeight="350" height="255">
				<el-tabs v-model="activeName" type="card">
					<el-tab-pane label="结算信息" name="payment">
						<DocumentList :isfinish="isfinish" :height="footHeight" v-model:initialData="dataForm.documentSettleDetailList"></DocumentList>
					</el-tab-pane>
				</el-tabs>
			</GrDocumentFoot>
			<el-card>
				<div class="footer-containers" style="width: 100%; display: flex;">
					<div class="footer-left">按单结算差异:<el-text tag="b" class="footer-left-text" type="danger"
							size="large">{{ differenceAmount }}</el-text>
						<el-divider direction="vertical" />
						本单可冲抵应收:<el-text tag="b" class="footer-left-text" type="danger" size="large">{{ totalPayAmount }}</el-text>
						<el-divider direction="vertical" />
						此前应收:<el-text tag="b" class="footer-left-text" type="danger"
							size="large">{{ historyReceivePayment }}</el-text>
						<el-divider direction="vertical" />
						合计应收:<el-text tag="b" class="footer-left-text" type="danger"
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

<script setup lang="ts" name="DocumentSettlementReceivePaymentIndex">
	import { useCrud } from '@/hooks'
	import { reactive, ref, onMounted, onUnmounted, computed, watch, watchEffect } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	import { useRouter, useRoute } from 'vue-router'
	import AccountList from '.././accountList/index.vue'
	import DocumentList from './documentList/index.vue'
	import { ElMessage } from 'element-plus/es'
	import { useDocumentSubmitApi, useGetDocumentCodeApi, useGetgetHistoryReceivePaymentApi, useDocumentApi } from '@/api/product/order'
	import { DocumentAccountDetailInt, documentDataInt } from '.././settlement'
	import { getCurrentDate } from '@/utils/tool'
	import { cloneDeep } from 'lodash-es'
	import { closeTab } from '@/utils/tabs'
	import { useWindowResize } from '@/views/document/useWindowResize'
	import { getContactunitsAdvanceOut } from '@/views/document/index'
	import { calcChain } from '@/utils/accuracyCalc'
	import Big from 'big.js'

	const isfinish = ref(false)
	const saveRef = ref()
	const amountType = ref('1')
	const documentType = ref('34')
	
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
		advanceInt: number | null
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
		advanceInt: null,
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
			advanceInt: null,
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
		getHistoryReceivePayment(dataForm.value.contactunitsId)
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
	const totalAmount = computed(() => {
		//此前应收款-当前收款
		return historyReceivePayment.value - dataForm.value.documentAccountDetailList.reduce((sum, item) => {
			return sum.plus(item.amount || 0)
		}, new Big(0)).toNumber()
	})

	//本单可冲抵应付
	const totalPayAmount = computed(() => {
		return dataForm.value.documentAccountDetailList.reduce((sum, item) => {
			return sum.plus(item.amount || 0)
		}, new Big(0)).toNumber()
	})
	
	//此前应收款
	const historyReceivePayment = ref(0)
	watch(
		() => dataForm.value.contactunitsId,
		newVal => {
			if(newVal){
				useGetgetHistoryReceivePaymentApi(newVal).then(res => {
					historyReceivePayment.value = res.data
				})
			}
		}
	)

	const getHistoryReceivePayment= (contactunitsId : number | null) =>{
		useGetgetHistoryReceivePaymentApi(contactunitsId).then(res => {
			historyReceivePayment.value = res.data
		})
	}
	
	//按单算差异
	const differenceAmount = computed(() => {
		return (calcChain(dataForm.value.documentAccountDetailList.reduce((sum, item) => {
			return sum.plus(item.amount || 0)
		}, new Big(0)).toNumber()).sub(dataForm.value.documentSettleDetailList.reduce((sum, item) => {
			return sum.plus(item.sumAmount || 0)
		}, new Big(0)).toNumber())
		)
	})
	
	const submitHandle = () => {
		saveRef.value.validate((valid : boolean) => {
			if (!valid) {
				return false
			}

			if (dataForm.value.documentSettleDetailList.length === 0) {
				ElMessage.error({
					message: '入库商品不能为空'
				})
			}
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
	
	const contactunitBalance = ref(0)
	//预付款余额
	const balance = ref(0)
	const updateBalance = async ()=>{
		if(dataForm.value.contactunitsId){
			contactunitBalance.value = await getContactunitsAdvanceOut(dataForm.value.contactunitsId)
			balance.value = calcChain(contactunitBalance.value).sub(dataForm.value.advanceInt).toNumber()
		}
	}
	
	watch(
	  ()=>dataForm.value.advanceInt,
	  (newAmount) => {
	    balance.value = calcChain(contactunitBalance.value).sub(newAmount || 0).toNumber()
	  }
	)
	
	// 引入窗口高度逻辑
	const { headHeight, listHeight, footHeight, occupyHeight, getHeadHeight, updateTableHeight,isArrowUpFu } = useWindowResize(227)
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