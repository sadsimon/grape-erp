<template>
	<div class="parent-container">
		<el-container class="layout-container">
			<el-card id="customParent" class="layout-query">
				<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList">
					<el-form-item prop="documentCode" label="单据编号">
						<el-input v-model="state.queryForm.documentCode"></el-input>
					</el-form-item>
					<el-form-item prop="contactunitsId" label="结算单位">
						<GrContactunitsInput v-model="state.queryForm.contactunitsId" readonly></GrContactunitsInput>
					</el-form-item>
					<el-form-item prop="userId" label="经手人">
						<MaUserInput v-model="state.queryForm.userId"></MaUserInput>
					</el-form-item>
					<el-form-item prop="documentTimes" label="单据时间">
						<el-date-picker
								v-model="state.queryForm.documentTimes"
								type="datetimerange"
								start-placeholder="开始时间"
								end-placeholder="结束时间"
								value-format="YYYY-MM-DD HH:mm:ss"
							  />
					</el-form-item>
					<el-form-item prop="types" label="单据类型">
						<el-select v-model="state.queryForm.types" multiple style="width: 240px" >
							<el-option label="支出单" value="36" />
							<el-option label="收入单" value="37" />
						</el-select>
					</el-form-item>
					<el-form-item>
						<el-button icon="Search" type="primary" @click="queryDataList">查询</el-button>
					</el-form-item>
					<el-form-item>
						<el-button icon="RefreshRight" @click="reset(queryRef)">重置</el-button>
					</el-form-item>
				</el-form>
			</el-card>

			<el-card class="main">
				<el-space>
					<el-space>
						<GrButton placeholder="新增单据" :data="addDocumentType" @select="addDocumentUrl($event)" width="120px">
							  <template #prefix>
									<el-icon><Plus /></el-icon>
							  </template>
						</GrButton>
					</el-space>
					<el-space>
						<el-button icon="Delete" plain type="danger" @click="deleteBatchHandle()">批量删除</el-button>
					</el-space>
				</el-space>

				<el-table
					v-loading="state.dataListLoading"
					show-overflow-tooltip
					:data="state.dataList"
					border
					class="layout-table"
					highlight-current-row
					@selection-change="selectionChangeHandle"
					@row-click="handleRow"
					:height="tableHeight"
				>
					<el-table-column type="selection" fixed="left" header-align="center" align="center" width="50"></el-table-column>
					<el-table-column label="操作" header-align="center" align="center" width="120">
						<template #default="scope">
							<el-button type="primary" link @click="changeHandle(scope.row.documentType,scope.row.id)">修改</el-button>
							<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
						</template>
					</el-table-column>
					<el-table-column prop="documentTime" label="录单时间" header-align="center" align="center" width="160"/>
					<el-table-column prop="documentCode" label="单据编号" header-align="center" align="center" width="170"/>
					<!-- <ma-dict-column prop="documentStatus" label="单据状态" dict-type="document_status" /> -->
					<ma-dict-column prop="documentType" label="单据类型" dict-type="document_type" />
					<el-table-column prop="contactunitsName" label="结算单位" header-align="center" align="center" />
					<el-table-column prop="realName" label="经手人" header-align="center" align="center" />
					<el-table-column prop="creatorName" label="制单人" header-align="center" align="center" />
					<el-table-column prop="finalAmount" label="单据金额" header-align="center" align="center" />
				</el-table>
				<el-pagination
							:current-page="state.page"
							:page-sizes="state.pageSizes"
							:page-size="state.limit"
							:total="state.total"
							layout="total, sizes, prev, pager, next, jumper"
							@size-change="sizeChangeHandle"
							@current-change="currentChangeHandle"
							:size="'small'"
						>
						</el-pagination>
						<div style="width: 100%"></div>
						
			</el-card>
			<GrDocumentFoot @isArrowUp="isArrowUpFu" height="220" maxHeight="300" >
				<el-tabs v-model="activeName" type="card">
					<el-tab-pane label="收/付款情况" name="income">
						<el-table border :data="accountDetailList" height="186px">
							<el-table-column width="200px" prop="accountName" :label="payType" header-align="center" align="center" />
							<el-table-column width="200px" prop="amount" :label="payAmount" header-align="center" align="center" />
							<el-table-column width="350px" prop="remark" label="备注" header-align="center" align="center" />
						</el-table>
					</el-tab-pane>
					<el-tab-pane label="费用查询" name="deteil">
						<el-table border :data="documentDetailList" height="186px">
							<el-table-column width="200px" prop="projectName" label="项目名称" header-align="center" align="center" />
							<el-table-column width="200px" prop="amount" label="费用金额" header-align="center" align="center" />
						</el-table>
					</el-tab-pane>
				</el-tabs>
			</GrDocumentFoot>
		</el-container>
	</div>
</template>

<script setup lang="ts" name="DocumentReportPaymentIndex">
import { useCrud } from '@/hooks'
import { reactive, ref, onMounted } from 'vue'
import { IHooksOptions } from '@/hooks/interface'
import { useRouter } from 'vue-router'
import { useGetDocumentDetailApi, useGetDocumentAccountDetailApi } from '@/api/product/order'
import { DocumentDetail,AccountDetail } from '@/views/document/index'



const showPopup = ref(false)

const queryRef = ref()

// 初始化表格高度
const tableHeight = ref(0)
const footHeight = ref(0)
// 更新表格高度的方法
const updateTableHeight = () => {
	tableHeight.value = window.innerHeight -(264+footHeight.value)
}
const isArrowUpFu = (isArrowUp: number) => {
	footHeight.value = isArrowUp
	updateTableHeight()
}
// 在组件挂载时初始化高度，并监听窗口大小变化
onMounted(() => {
	updateTableHeight() // 初始化高度
	window.addEventListener('resize', updateTableHeight) // 监听窗口大小变化
})

const router = useRouter()

// 修改
const changeHandle = (documentType: string, id: number) => {
	let path
	let query = {}
	if(documentType === '36'){
		path = '/settlement/expend/index'
	}else if(documentType === '37'){
		path = '/settlement/income/index'
	}
	query = { id: id.toString() }
	router.push({path,query})
}

const state: IHooksOptions = reactive({
	dataListUrl: '/order/grDocument/page',
	deleteUrl: '/order/grDocument',
	queryForm: {
		documentCode: '',
		contactunitsId: '',
		userId: '',
		documentTimes: [] as string[],
		types: [] as string[],
		module: 'income'
	}
})

const activeName = ref('income')

const addOrUpdateRef = ref()
const addOrUpdateHandle = (id?: Number, row?: any) => {
	addOrUpdateRef.value.init(id, row)
}

const accountDetailList = reactive<AccountDetail[]>([])
const documentDetailList = reactive<DocumentDetail[]>([])
const payType =  ref('付款账户')
const payAmount =  ref('付款金额')
const handleRow = (row: any) =>{
	if(row.documentType === '36'){
		payType.value = '付款账户'
		payAmount.value = '付款账金额'
	}else if(row.documentType === '37'){
		payType.value = '收款账户'
		payAmount.value = '收款金额'
	}
	useGetDocumentAccountDetailApi(row.id).then(res => {
		 accountDetailList.splice(0, accountDetailList.length, ...res.data)
	})
	useGetDocumentDetailApi(row.id).then(res => {
		documentDetailList.splice(0, documentDetailList.length, ...res.data)
	})
}

const queryDataList =() =>{
	accountDetailList.length = 0
	documentDetailList.length = 0
	getDataList()
}

const addDocumentType = ref([
  { "id": '36', "name": "支出单" },
  { "id": '37', "name": "收入单" }
])

const addDocumentUrl = (data: any) =>{
	let path
	if(data.id === '36'){
		path = '/settlement/expend/index'
	}else if(data.id === '37'){
		path = '/settlement/income/index'
	}
	router.push({path})
}

const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
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
	.main {
		flex: 1;
		margin-bottom: 5px;
	}
	.el-pagination {
		 margin-top: 1px !important;
	}
	
</style>
