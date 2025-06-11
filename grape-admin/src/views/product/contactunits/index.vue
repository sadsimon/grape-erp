<template>
	<el-card class="layout-query">
		<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList">
			<el-form-item prop="categoryId">
				<ma-data-tree-select
					v-model="state.queryForm.categoryId"
					url="/product/grContactunitsCategory/list"
					:props="{ label: 'categoryName', children: 'children' }"
					placeholder="往来单位类别"
				></ma-data-tree-select>
			</el-form-item>
			<el-form-item style="width: 400px" prop="param">
				<el-input v-model="state.queryForm.param" placeholder="按编号/名称/联系人/电话查询"></el-input>
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
					<el-button type="primary" link @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
					<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
				</template>
			</el-table-column>
			<el-table-column prop="categoryName" label="类别" header-align="center" align="center" />
			<ma-dict-column prop="type" label="类型" dict-type="contactunits_type" />
			<el-table-column prop="contactunitsCode" label="编号" header-align="center" align="center"></el-table-column>
			<el-table-column prop="contactunitsName" label="名称" header-align="center" align="center"></el-table-column>
			<el-table-column prop="rate" label="增值税率%" header-align="center" align="center" />
			<el-table-column prop="bankNumber" label="银行账号" header-align="center" align="center" />
			<el-table-column prop="contactName" label="首要联系人" header-align="center" align="center"></el-table-column>
			<el-table-column prop="mobile" label="手机" header-align="center" align="center"></el-table-column>
			<el-table-column prop="landline" label="座机" header-align="center" align="center"></el-table-column>
			<el-table-column prop="netContact" label="QQ/微信/Email" header-align="center" align="center"></el-table-column>
			<el-table-column prop="address" label="联系地址" header-align="center" align="center">
				<template #default="scope">
					{{ scope.row.province }}{{ scope.row.province ? ',' : '' }} {{ scope.row.city }}{{ scope.row.city ? ',' : '' }} {{ scope.row.district
					}}{{ scope.row.district ? ',' : '' }}
					{{ scope.row.address }}
				</template>
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
		<add-or-update ref="addOrUpdateRef" @refresh-data-list="getDataList"></add-or-update>
	</el-card>
</template>

<script setup lang="ts" name="ProductContactunitsIndex">
import { useCrud } from '@/hooks'
import { reactive, ref } from 'vue'
import AddOrUpdate from './add-or-update.vue'
import { IHooksOptions } from '@/hooks/interface'

const queryRef = ref()

const state: IHooksOptions = reactive({
	dataListUrl: '/product/grContactunits/page',
	deleteUrl: '/product/grContactunits',
	queryForm: {
		categoryId: '',
		contactunitsCode: ''
	}
})

const addOrUpdateRef = ref()
const addOrUpdateHandle = (id?: Number) => {
	addOrUpdateRef.value.init(id)
}

const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
</script>
