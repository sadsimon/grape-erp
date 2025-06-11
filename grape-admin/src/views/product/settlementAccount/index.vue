<template>
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
			:data="state.dataList"
			border
			show-overflow-tooltip
			class="layout-table"
			row-key="id"
			max-height="300"
			@selection-change="selectionChangeHandle"
			@sort-change="sortChangeHandle"
		>
			<el-table-column type="selection" fixed="left" header-align="center" align="center" width="50"></el-table-column>
			<el-table-column label="操作" header-align="center" align="center" width="120">
				<template #default="scope">
					<el-button type="primary" link @click="addOrUpdateHandle(scope.row.id, scope.row)">修改</el-button>
					<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
				</template>
			</el-table-column>
			<el-table-column prop="accountCode" label="账户编号" header-align="center" align="center" />
			<el-table-column prop="accountName" label="账户名称" header-align="center" align="center"></el-table-column>
			<!-- 			<el-table-column prop="" label="当前余额" header-align="center" align="center"></el-table-column>
 -->
			<el-table-column prop="openingBalance" label="期初余额" header-align="center" align="center"></el-table-column>
			<ma-dict-column prop="accountType" label="账户类型" dict-type="account_type"></ma-dict-column>
			<el-table-column label="状态" header-align="center" align="center">
				<template #default="{ row }"><GrDataSwitch :id="row.id" v-model="row.state" url="/product/grSettlementAccount" /></template>
			</el-table-column>
		</el-table>
		<!-- 新增 / 修改 -->
		<AddOrUpdate ref="addOrUpdateRef" @refresh-data-list="getDataList"></AddOrUpdate>
	</el-card>
</template>

<script setup lang="ts" name="Contactunits">
import { useCrud } from '@/hooks'
import { reactive, ref } from 'vue'
import { IHooksOptions } from '@/hooks/interface'
import AddOrUpdate from './add-or-update.vue'

const queryRef = ref()

const state: IHooksOptions = reactive({
	dataListUrl: '/product/grSettlementAccount/list',
	deleteUrl: '/product/grSettlementAccount',
	isPage: false
})

const addOrUpdateRef = ref()
const addOrUpdateHandle = (id?: Number, row?: any) => {
	addOrUpdateRef.value.init(id, row)
}

const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
</script>
