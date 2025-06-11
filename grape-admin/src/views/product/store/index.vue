<template>
	<el-card>
		<el-space>
			<el-space>
				<el-button icon="Plus" type="primary" @click="addOrUpdateHandle(false)">新增</el-button>
			</el-space>
			<!-- <el-space>
				<el-button icon="Delete" plain type="danger" @click="deleteBatchHandle()">批量删除</el-button>
			</el-space> -->
		</el-space>
		<el-table
			v-loading="state.dataListLoading"
			:data="state.dataList"
			border
			show-overflow-tooltip
			class="layout-table"
			row-key="id"
			@selection-change="selectionChangeHandle"
			@sort-change="sortChangeHandle"
		>
			<el-table-column label="操作" header-align="center" align="center" width="120">
				<template #default="scope">
					<el-button type="primary" link @click="addOrUpdateHandle(scope.row.id, scope.row)">修改</el-button>
					<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
				</template>
			</el-table-column>
			<el-table-column prop="storeNumber" label="仓库编号" header-align="center" align="center"> </el-table-column>
			<el-table-column prop="storeName" label="仓库名称" header-align="center" align="center"> </el-table-column>
			<el-table-column prop="username" label="仓管" header-align="center" align="center"></el-table-column>
			<el-table-column label="状态" header-align="center" align="center">
				<template #default="{ row }"><GrDataSwitch :id="row.id" v-model="row.state" url="/product/grStore" /></template>
			</el-table-column>
		</el-table>
		<StoreAddOrUpdate ref="addOrUpdateRef" @refresh-data-list="getDataList"></StoreAddOrUpdate>
	</el-card>
</template>

<script setup lang="ts">
import { useCrud } from '@/hooks'
import { reactive, ref } from 'vue'
import { IHooksOptions } from '@/hooks/interface'

const state: IHooksOptions = reactive({
	dataListUrl: '/product/grStore/list',
	deleteUrl: '/product/grStore',
	isPage: false
})

const addOrUpdateRef = ref()
const addOrUpdateHandle = (isUpdate: Boolean, row?: any) => {
	addOrUpdateRef.value.init(isUpdate, row)
}

const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
</script>

<style></style>
