<template>
	<GrDialog to-teleport="#dialog-container" :model-value="visible" title="选择单据" draggable @close="handleClose">
		<el-card class="layout-query">
			<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList">
				<el-form-item prop="documentTimes" label="单据时间">
					<el-date-picker
							v-model="state.queryForm.documentTimes"
							type="datetimerange"
							start-placeholder="开始时间"
							end-placeholder="结束时间"
							value-format="YYYY-MM-DD HH:mm:ss"
							
						  />
				</el-form-item>
				<el-form-item prop="documentType" label="单据类型">
					<el-select v-model="state.queryForm.types" multiple style="width: 240px" >
						  <el-option label="采购入库" value="02" />
						  <el-option label="采购退货" value="03" />
					</el-select>
				</el-form-item>
				<el-form-item prop="documentCode" label="单据编号">
					<el-input v-model="state.queryForm.documentCode"></el-input>
				</el-form-item>
				<el-form-item prop="documentCode" label="经手人">
					<el-input v-model="state.queryForm.userId"></el-input>
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
			<el-table
				v-loading="state.dataListLoading"
				:data="state.dataList"
				border
				style="width: 100%"
				highlight-current-row
				show-overflow-tooltip
				:row-key="rowKey"
				@row-dblclick="handleRowClick"
				@selection-change="selectionChangeHandle"
				@current-change="currentChange"
			>
				<el-table-column v-if="multiple" type="selection" header-align="center" align="center" width="50"></el-table-column>
				<el-table-column prop="documentTime" label="录单时间" width="160" header-align="center" align="center"></el-table-column>
				<el-table-column prop="documentCode" label="单据编号" width="160" header-align="center" align="center"></el-table-column>
				<ma-dict-column prop="documentType" label="单据类型" width="100" dict-type="document_type" />
				<el-table-column prop="contactunitsName" label="结算单位" header-align="center" align="center"></el-table-column>
				<el-table-column prop="realName" label="经手人" header-align="center" align="center"></el-table-column>
				<el-table-column prop="pendingAmount" label="待结算金额" header-align="center" align="center"></el-table-column>
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
		<template #footer>
			<el-button @click="handleClose">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts">
import { useCrud } from '@/hooks'
import { reactive, ref, watch } from 'vue'
import { IHooksOptions } from '@/hooks/interface'
import { ElMessage } from 'element-plus'

const props = defineProps({
	multiple: {
		type: Boolean,
		required: false,
		default: true
	},
	visible: {
		type: Boolean,
		default: false
	},
	rowKey: {
		type: String,
		required: false,
		default: ''
	}
})

const queryRef = ref()

const state: IHooksOptions = reactive({ 
	dataListUrl: '/order/grDocument/pageCheckList',
	queryForm: {
		documentTimes: [],
		types: [] as string[],
		documentCode: '',
		userId: null
	},
	dataListSelections: []
})

const treeVisible = ref(false)
const currentRow = ref()
const currentChange = (row: any) => {
	currentRow.value = row
}

const emit = defineEmits(['select', 'update:visible'])
const submitHandle = () => {
	// 多选
	if (props.multiple) {
		const dataList = state.dataListSelections ? state.dataListSelections : []
		if (dataList.length === 0) {
			ElMessage.warning('请选择记录')
			return
		}
		handleClose()
		emit('select', dataList)
		return
	}

	// 单选
	if (!currentRow.value) {
		ElMessage.warning('请选择记录')
		return
	}
	handleClose()
	emit('select', [currentRow.value])
}

const handleRowClick = (row: any) => {
	handleClose()
	emit('select', [row])
}

const handleClose = () => {
	emit('update:visible', false)
}

const handleClear = () => {
	getDataList()
}

const { getDataList, selectionChangeHandle, sizeChangeHandle, currentChangeHandle, reset } = useCrud(state)
</script>

<style scoped>
.custom-dialog {
	max-height: calc(100vh - 100px);
	/* 根据需要调整 */
	overflow-y: auto;
	/* 启用垂直滚动 */
}
</style>
