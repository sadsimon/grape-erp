<template>
	<el-card class="layout-query">
		<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList">
			<el-form-item style="width: 400px" prop="param">
				<el-input v-model="state.queryForm.param" placeholder="按收支名称查询"></el-input>
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
				<el-button icon="Plus" type="primary" @click="addOrUpdateHandle()">新增</el-button>
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
		>
			<el-table-column type="selection" fixed="left" header-align="center" align="center" width="50"></el-table-column>
			<el-table-column label="操作" header-align="center" align="center" width="120">
				<template #default="scope">
					<el-button type="primary" link @click="addOrUpdateHandle(scope.row.id, scope.row)">修改</el-button>
					<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
				</template>
			</el-table-column>
			<el-table-column prop="name" label="名称" header-align="center" align="center"></el-table-column>
			<ma-dict-column prop="amountType" label="收支类型" dict-type="amount_type"></ma-dict-column>
			<el-table-column label="状态" header-align="center" align="center">
				<template #default="{ row }"><GrDataSwitch :id="row.id" v-model="row.state" url="/product/grIncomeExpend" /></template>
			</el-table-column>
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
		<IncomeExpendAddOrUpdate ref="addOrUpdateRef" @refresh-data-list="getDataList"></IncomeExpendAddOrUpdate>
	</el-card>
</template>

<script setup lang="ts" name="ProductIncomeExpenditureIndex">
import { useCrud } from '@/hooks'
import { reactive, ref } from 'vue'
import { IHooksOptions } from '@/hooks/interface'

const queryRef = ref()

const state: IHooksOptions = reactive({
	dataListUrl: '/product/grIncomeExpend/page',
	deleteUrl: '/product/grIncomeExpend',
	queryForm: {
		param: ''
	}
})

const addOrUpdateRef = ref()
const addOrUpdateHandle = (id?: Number, row?: any) => {
	addOrUpdateRef.value.init(id, row)
}

const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
</script>
