<template>
	<div class="parent-container">
		<GrStamp :textValue="'已过账'" :isShow="isfinish"/>
		<el-container class="layout-container">
			<el-card class="head layout-query">
				<el-form ref="saveRef" :inline="true" :rules="dataRules" :model="dataForm">
					<div style="font-size:20px; text-align:center;margin-bottom: 15px;">其他出库单</div>
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
				<el-space>
					<el-input :disabled="isfinish" v-model="barcodeQuery" style="width: 350px" placeholder="扫码枪精准匹配"
						@keydown.enter="checkByBarcode">
						<template #prepend>条码</template>
					</el-input>
					<GrFile :businessCode="dataForm.documentCode" />
				</el-space>
				
				<List ref="detailListRef" :isfinish="isfinish" :height="listHeight" v-model:initialData="dataForm.documentDetailList"></List>
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
</template>

<script setup lang="ts" name="DocumentOtherOutIndex">
	import { useCrud } from '@/hooks'
	import { reactive, ref, onMounted, onUnmounted, computed, watch } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	import { useRouter, useRoute } from 'vue-router'
	import List from './list/index.vue'
	import { ElMessage } from 'element-plus/es'
	import { useDocumentSubmitApi, useGetDocumentCodeApi, useGetHistoryPayAmountApi } from '@/api/product/order'
	import { DocumentDetail } from '@/views/document/index'
	import { useWindowResize } from '@/views/document/useWindowResize'
	import { Delete } from '@element-plus/icons-vue'
	import { getCurrentDate } from '@/utils/tool'
	import { cloneDeep } from 'lodash-es'
	import { closeTab } from '@/utils/tabs'

	const isfinish = ref(false)

	const saveRef = ref()
	
	const amountType = ref('3')
	const documentType = ref('23')
	const barcodeQuery = ref('')
	
	interface DataForm {
		contactunitsId : number | null
		userId : number | null
		remark : string
		documentCode : string
		documentTime : string
		finalAmount : number | null
		documentStatus : string
		documentType: string
		amountType: string
		documentDetailList : DocumentDetail[]
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
			'expectPurchasePrice': null
		}]
	}
	
	const dataForm = ref<DataForm>(initialDataForm)
	
	onMounted(() => {
		init()
	})

	const addNext =() => {
		init()
	}
	
	const detailListRef = ref()
	const copyNext =() =>{
		getDocumentCode()
		dataForm.value.documentTime = getCurrentDate()
		isfinish.value = false
		detailListRef.value.refresh()
		ElMessage.success({
			message: '复制成功'
		})
	}
	
	const route = useRoute()
	const router = useRouter();
	const closeWindow = () => {
	  closeTab(router, route)
	}
	
	const init = async () => {
		dataForm.value = cloneDeep(initialDataForm)
		getDocumentCode()
		dataForm.value.documentTime = getCurrentDate()
		isfinish.value = false
	}
	const getDocumentCode = () => {
		useGetDocumentCodeApi(documentType.value).then(res => {
			dataForm.value.documentCode = res.data
		})
	}

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
	
	const checkByBarcode = () => {
		detailListRef.value.checkByBarcode(barcodeQuery.value)
		barcodeQuery.value =''
	}
	
	// 引入窗口高度逻辑
	const { headHeight, listHeight, footHeight, occupyHeight, getHeadHeight, updateTableHeight,isArrowUpFu } = useWindowResize(190)
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