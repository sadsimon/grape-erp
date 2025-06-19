<template>
	<div class="parent-container">
		<GrStamp :textValue="'已过账'" :isShow="isfinish"/>
		<el-container class="layout-container">
			<el-card class="head layout-query">
				<el-form ref="saveRef" :inline="true" :rules="dataRules" :model="dataForm">
					<div style="font-size:20px; text-align:center;margin-bottom: 15px;">其他入库单</div>
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
				</el-form>
			</el-card>
			<el-card class="main">
				<List ref="listRef" :isfinish="isfinish" :height="listHeight" v-model:initialData="dataForm.documentDetailList"></List>
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

<script setup lang="ts" name="StockOtherInIndex">
	import { useCrud } from '@/hooks'
	import { reactive, ref, onMounted, onUnmounted, computed, watch } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	import { useRouter, useRoute } from 'vue-router'
	import List from './list/index.vue'
	import { ElMessage } from 'element-plus/es'
	import { useDocumentSubmitApi, useGetDocumentCodeApi, useGetHistoryPayAmountApi } from '@/api/product/order'
	import { dataInt } from '@/views/purchase/purchase'
	import { Delete } from '@element-plus/icons-vue'
	import { getCurrentDate } from '@/utils/tool'
	import { cloneDeep } from 'lodash-es'
	import { closeTab } from '@/utils/tabs'

	const isfinish = ref(false)

	const saveRef = ref()
	
	const amountType = ref('3')
	const documentType = ref('22')
	
	interface DataForm {
		contactunitsId : number | null
		userId : number | null
		documentCode : string
		documentTime : string
		finalAmount : number | null
		documentStatus : string
		documentType: string
		documentDetailList : dataInt[]
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
			'expectPurchasePrice': null,
			
		}]
	}
	
	const dataForm = ref<DataForm>(initialDataForm)
	
	onMounted(() => {
		init()
	})

	const addNext =() => {
		init()
	}
	
	const listRef = ref()
	const copyNext =() =>{
		getDocumentCode()
		dataForm.value.documentTime = getCurrentDate()
		isfinish.value = false
		listRef.value.refresh()
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
	
	//====================================窗口高度部分start====================================
	const listHeight = ref(0)
	const footHeight = ref(0)
	const occupyHeight = ref(290)//其他部分占用高度
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