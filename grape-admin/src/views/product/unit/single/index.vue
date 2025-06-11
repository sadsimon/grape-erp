<template>
	<el-space direction="vertical">
		<el-space>
			<el-text tag="b" size="large" class="mx-1">单计量单位</el-text>
		</el-space>
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
		max-height="300"
		row-key="id"
		@selection-change="selectionChangeHandle"
		@sort-change="sortChangeHandle"
	>
		<el-table-column label="操作" fixed="left" header-align="center" align="center" width="200">
			<template #default="scope">
				<el-button type="primary" link @click="addOrUpdateHandle(scope.row.id, scope.row)">修改</el-button>
				<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
			</template>
		</el-table-column>
		<el-table-column prop="name" label="名称" header-align="center" align="center"> </el-table-column>
	</el-table>

	<UnitSingleAddOrUpdate ref="addOrUpdateRef" @refresh-data-list="getDataList"></UnitSingleAddOrUpdate>
</template>

<script setup lang="ts">
import { useCrud } from '@/hooks'
import { reactive, ref } from 'vue'
import { IHooksOptions } from '@/hooks/interface'

const state: IHooksOptions = reactive({
	dataListUrl: '/product/grUnit/list/1',
	deleteUrl: '/product/grUnit',
	isPage: false,
	queryForm: {
		model: 1
	}
})

const addOrUpdateRef = ref()
const addOrUpdateHandle = (isUpdate: Boolean, row?: any) => {
	addOrUpdateRef.value.init(isUpdate, row)
}

const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
</script>

<style></style>
