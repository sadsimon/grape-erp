<template>
	<div class="parent-container">
		<el-container class="layout-container">
			<el-card id="customParent" class="head layout-query">
				<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList">
					<el-form-item prop="documentCode" label="单据编号">
						<el-input v-model="state.queryForm.documentCode"></el-input>
					</el-form-item>
					<el-form-item prop="contactunitsId" label="往来单位">
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
							  <el-option label="销售出库单" value="13" />
							  <el-option label="销售退货单" value="14" />
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
						<GrButton placeholder="新增单据" :data="addDocumentType" @select="addDocument($event)" width="120px">
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
					@selection-change="selectionChangeHandle"
					highlight-current-row
					@row-click="handleRow"
					:height="listHeight"
				>
					<el-table-column type="selection" fixed="left" header-align="center" align="center" width="50"></el-table-column>
					<el-table-column label="操作" header-align="center" align="center" width="120">
						<template #default="scope">
							<el-button type="primary" link @click="editDocument(scope.row.documentType,scope.row.id)">修改</el-button>
							<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
						</template>
					</el-table-column>
					<el-table-column prop="documentTime" label="录单时间" header-align="center" align="center" width="160"/>
					<el-table-column prop="documentCode" label="单据编号" header-align="center" align="center" width="170"/>
					<ma-dict-column prop="documentStatus" label="单据状态" dict-type="document_status" />
					<ma-dict-column prop="documentType" label="单据类型" dict-type="document_type" />
					<el-table-column prop="contactunitsName" label="供货单位" header-align="center" align="center" />
					<el-table-column prop="realName" label="经手人" header-align="center" align="center" />
					<el-table-column prop="creatorName" label="制单人" header-align="center" align="center" />
					
					<el-table-column prop="finalAmount" label="采购金额" header-align="center" align="center" />
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
			<GrDocumentFoot @isArrowUp="isArrowUpFu" :height="220" maxHeight="300" >
				<el-tabs v-model="activeName" type="card">
					<el-tab-pane label="出库明细" name="store">
						<el-table border :data="documentDetailList" height="186px">
							<el-table-column prop="img" label="图片" class-name="column-image" header-align="center" align="center">
								<template #default="scope">
									<el-popover v-if="scope.row['imgList'][0]" placement="right" :width="278">
										<template #reference>
											<el-image style="width: 32px; height: 32px" :src="scope.row['imgList'][0]" fit="contain" />
										</template>
										<el-image style="width: 250px; height: 250px" :src="scope.row['imgList'][0]" fit="contain" />
									</el-popover>
								</template>
							</el-table-column>
							<el-table-column prop="productName" label="商品名称" header-align="center" align="center" />
							<el-table-column prop="productNumber" label="商品编码" header-align="center" align="center" />
							<el-table-column prop="barcode" label="条码" header-align="center" align="center" />
							<el-table-column prop="storeName" :label="documentType" header-align="center" align="center" />
							<el-table-column prop="quantity" label="数量" header-align="center" align="center" />
							<el-table-column prop="unitName" label="单位" header-align="center" align="center" />
							<el-table-column prop="unitPrice" label="单价" header-align="center" align="center" />
							<el-table-column prop="amount" label="金额" header-align="center" align="center" />
						</el-table>
					</el-tab-pane>
					<el-tab-pane label="结算明细" name="settlement">
						<el-table border :data="accountDetailList" height="186px">
							<el-table-column width="200px" prop="accountName" label="收款账户" header-align="center" align="center" />
							<el-table-column width="200px" prop="amount" label="收款明细" header-align="center" align="center" />
						</el-table>
					</el-tab-pane>
				</el-tabs>
			</GrDocumentFoot>
		</el-container>
	</div>
</template>

<script setup lang="ts" name="DocumentReportSaleIndex">
import { useCrud } from '@/hooks'
import { reactive, ref, onMounted } from 'vue'
import { IHooksOptions } from '@/hooks/interface'
import { useRouter } from 'vue-router'
import { useGetDocumentDetailApi, useGetDocumentAccountDetailApi } from '@/api/product/order'
import { DocumentDetail, AccountDetail, getRouterPath } from '@/views/document/index'
import { useWindowResize } from '@/views/document/useWindowResize'


const showPopup = ref(false)

const queryRef = ref()

const router = useRouter()

//新增单据
const addDocument = (data: any) =>{
	router.push({path: getRouterPath(data.id)})
}
// 修改单据
const editDocument = (documentType: string, id: number) => {
	router.push({path: getRouterPath(documentType),
    query: { id: id.toString() } })
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
		module: 'sale'
	}
})

const activeName = ref('store')

const addOrUpdateRef = ref()
const addOrUpdateHandle = (id?: Number, row?: any) => {
	addOrUpdateRef.value.init(id, row)
}

const documentDetailList = reactive<DocumentDetail[]>([]);
const accountDetailList = reactive<AccountDetail[]>([]);
const documentType =  ref('出库仓库')
const handleRow = (row: any) =>{
	if(row.documentType === '02'){
		documentType.value = '入库仓库'
	}else if(row.documentType === '03'){
		documentType.value = '出库仓库'
	}
	useGetDocumentDetailApi(row.id).then(res => {
		 documentDetailList.splice(0, documentDetailList.length, ...res.data);
	})
	useGetDocumentAccountDetailApi(row.id).then(res => {
		 accountDetailList.splice(0, accountDetailList.length, ...res.data);
	})
}

const queryDataList =() =>{
	documentDetailList.length = 0;
	accountDetailList.length = 0;
	getDataList()
}

const addDocumentType = ref([
  { "id": '13', "name": "销售出库单" },
  { "id": '14', "name": "销售退货单" }
])

const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)

// 引入窗口高度逻辑
const { headHeight, listHeight, footHeight, occupyHeight, getHeadHeight, updateTableHeight,isArrowUpFu } = useWindowResize(165)
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
