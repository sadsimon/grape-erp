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
					<el-form-item style="width: 400px" prop="param">
						<el-input v-model="state.queryForm.param" placeholder="按商品编号/名称/条码/规格型号/供应商/品牌查询"></el-input>
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
						<el-button icon="Plus" type="primary" @click="addOrUpdateHandle(false)">新增</el-button>
					</el-space>
					<el-space>
						<el-button icon="Delete" plain type="danger" @click="deleteBatchHandle()">批量删除</el-button>
					</el-space>
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
					<el-table-column label="操作" header-align="center" align="center" width="120">
						<template #default="scope">
							<el-button type="primary" link @click="addOrUpdateHandle(true, scope.row.id)">修改</el-button>
							<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
						</template>
					</el-table-column>
					<el-table-column v-if="state.field?.number" prop="number" min-width="90px" label="商品编码" header-align="center" align="center"> </el-table-column>
					<el-table-column v-if="state.field?.name" prop="name" min-width="90px" label="商品名称" header-align="center" align="center"></el-table-column>
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
					<el-table-column prop="categoryName" label="类别" header-align="center" align="center"></el-table-column>
					<el-table-column prop="specs" label="规格" header-align="center" align="center"></el-table-column>
					<el-table-column prop="unitName" label="单位" header-align="center" align="center"></el-table-column>
					<el-table-column prop="brandName" label="品牌" header-align="center" align="center" />
					<el-table-column prop="retailPrice" label="零售价" header-align="center" align="center" />
					<el-table-column prop="wholesalePrice" label="批发价" header-align="center" align="center" />
					<el-table-column prop="membershipPrice" label="VIP会员价" min-width="90px" header-align="center" align="center" />
					<el-table-column prop="createTime" label="创建时间" sortable="createTime" header-align="center" align="center" width="160" />
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
				<!-- 新增 / 修改 -->
				<add-or-update ref="addOrUpdateRef" @refresh-data-list="getDataList"></add-or-update>
			</el-card>
		</el-col>
	</el-row>
</template>

<script setup lang="ts" name="ProductProductIndex">
import { useCrud } from '@/hooks'
import { reactive, ref, onMounted } from 'vue'
import AddOrUpdate from './add-or-update.vue'
import { IHooksOptions } from '@/hooks/interface'
import { useDictTypeListApi } from '@/api/sys/dict'

const state: IHooksOptions = reactive({
	dataListUrl: '/product/grProduct/page',
	deleteUrl: '/product/grProduct',
	queryForm: {
		name: '',
		number: ''
	},
	columnCode: 'producList',
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
