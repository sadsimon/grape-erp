<template>
	<el-row :gutter="5">
		<el-col :span="3">
			<ma-data-tree-left
				url="/product/grProductCategory/list"
				:props="{ label: 'categoryName', children: 'children' }"
				title="类别列表"
				tree-data
				@click="handleClick"
				@manage="treeVisible = true"
			/>
		</el-col>
		<el-col :span="21">
			<el-card class="layout-query">
				<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList">
					<el-form-item style="width: 400px" label="商品信息" prop="param">
						<el-input v-model="state.queryForm.param" placeholder="按商品编号/名称/条码/规格型号/供应商/品牌查询"></el-input>
					</el-form-item>
					<el-form-item prop="storeIdList" label="仓库">
						<GrStoreMultipleInput v-model="state.queryForm.storeIdList"></GrStoreMultipleInput>
					</el-form-item>
					<el-form-item>
						<el-button icon="Search" type="primary" @click="getDataList()">查询</el-button>
					</el-form-item>
					<el-form-item>
						<el-button icon="RefreshRight" @click="reset(queryRef)">重置</el-button>
					</el-form-item>
				</el-form>
			</el-card>

			<el-card>
				<el-space>
					<el-space>
						<GrField @update:model="handleModelUpdate" :columns="state.columns" :columnCode="state.columnCode"/>
					</el-space>
				</el-space>

				<el-table
					v-loading="state.dataListLoading"
					:data="state.dataList"
					border
					show-overflow-tooltip
					class="layout-table"
					row-key="id"
					lazy
					:load="load"
					@selection-change="selectionChangeHandle"
					@sort-change="sortChangeHandle"
				>
					<el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
					<el-table-column prop="barcode" label="包装条码" header-align="center" align="center"> </el-table-column>
					<el-table-column  prop="number" label="商品编码" header-align="center" align="center"> </el-table-column>
					<el-table-column  prop="name" label="商品名称" min-width="90px" header-align="center" align="center"></el-table-column>
					<!-- <el-table-column prop="img" label="图片" class-name="column-image" header-align="center" align="center">
						<template #default="scope">
							<el-popover v-if="scope.row['imgList'][0]" placement="right" :width="278">
								<template #reference>
									<el-image style="width: 32px; height: 32px" :src="scope.row['imgList'][0]" fit="contain" />
								</template>
								<el-image style="width: 250px; height: 250px" :src="scope.row['imgList'][0]" fit="contain" />
							</el-popover>
						</template>
					</el-table-column> -->
					<el-table-column prop="category_name" label="类别" header-align="center" align="center"></el-table-column>
					<el-table-column prop="specs" label="规格" header-align="center" align="center"></el-table-column>
					<el-table-column prop="unit_name" label="单位" header-align="center" align="center"></el-table-column>
					<el-table-column prop="brand_name" label="品牌" header-align="center" align="center" />
					<el-table-column prop="stock_count" label="账面库存" header-align="center" align="center" />
					<el-table-column prop="retail_price" label="零售价" header-align="center" align="center" />
					<el-table-column prop="wholesale_price" label="批发价" header-align="center" align="center" />
					<el-table-column prop="membership_price" label="VIP会员价" min-width="90px" header-align="center" align="center" />
					<el-table-column prop="create_time" label="创建时间" sortable="createTime" header-align="center" align="center" width="180" />
				</el-table>
				<el-pagination
					:current-page="state.page"
					:page-sizes="state.pageSizes"
					:page-size="state.limit"
					:total="state.total"
					layout="total, sizes, prev, pager, next, jumper"
					@size-change="sizeChangeHandle"
					@current-change="currentChangeHandle"
				>
				</el-pagination>
				
			</el-card>
		</el-col>
	</el-row>
</template>

<script setup lang="ts" name="DocumentReportInventoryStatusIndex">
import { useCrud } from '@/hooks'
import { reactive, ref, onMounted } from 'vue'
import { IHooksOptions } from '@/hooks/interface'
import { useDictTypeListApi } from '@/api/sys/dict'

const state: IHooksOptions = reactive({
	dataListUrl: '/stock/grCurrentStock/inventoryStatusListPage',
	queryForm: {
		name: '',
		number: ''
	},
	columnCode: 'inventoryStatus',
	field: {}
})

const handleModelUpdate = (newModel: Record<string, boolean>) => {
  state.field = newModel;
}

// 当数据变化时，处理刷新
const nodeMap = new Map()
const load = (tree: any, treeNode: unknown, resolve: (data: any[]) => void) => {
	if (!nodeMap.has(tree.id)) {
		nodeMap.set(tree.id, { tree, treeNode, resolve })
	}

	useDictTypeListApi(tree.id).then((res: any) => {
		if (res.data.length > 0) {
			resolve(res.data)
		} else {
			resolve([])
		}
	})
}
const queryRef = ref()
const addOrUpdateRef = ref()
const addOrUpdateHandle = (isUpdate: Boolean, id?: number) => {
	addOrUpdateRef.value.init(isUpdate, id)
}

const handleClick = (categoryIdList: any[]) => {
	state.queryForm.categoryIdList = categoryIdList
	getDataList()
}

const treeVisible = ref(false)

const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset, getField } = useCrud(state)
</script>

<style scoped>
	
</style>
