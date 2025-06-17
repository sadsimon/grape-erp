<template>
	<GrDialog :title :model-value="visible"  draggable @close="handleClose">
		<el-card class="layout-query">
			<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList">
				<el-form-item style="width: 400px" prop="param">
					<el-input
						v-model="state.queryForm.param"
						clearable
						placeholder="按编号/名称/联系人/电话查询"
						@clear="handleClear"
					></el-input>
				</el-form-item>
				<el-form-item>
					<el-button icon="Search" type="primary" @click="getDataList()">查询</el-button>
				</el-form-item>
			</el-form>
		</el-card>
		<el-row :gutter="5">
			<el-col :span="5">
				<div>
					<MaDataTreeLeft2
						url="/product/grContactunitsCategory/list"
						:props="{ label: 'categoryName', children: 'children' }"
						tree-data
						@click="handleClick"
						@manage="treeVisible = true"
					/>
				</div>
			</el-col>
			<el-col :span="19">
					<el-table
						v-loading="state.dataListLoading"
						height="400px"
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
						<el-table-column prop="categoryName" label="类别" min-width="100px" header-align="center" align="center"></el-table-column>
						<el-table-column prop="contactunitsCode" label="编号" min-width="100px" header-align="center" align="center"></el-table-column>
						<el-table-column prop="contactunitsName" label="名称" min-width="100px" header-align="center" align="center"></el-table-column>
						<el-table-column prop="contactName" label="联系人" header-align="center" align="center"></el-table-column>
						<el-table-column prop="mobile" label="手机" header-align="center" align="center"></el-table-column>
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
			</el-col>
		</el-row>
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
import { useDictTypeListApi } from '@/api/sys/dict'

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
	},
	title: {
		type: String,
		required: true,
		default: '往来单位'
	},
	type: {
		type: String,
		required: true,
	}
})
		
const state: IHooksOptions = reactive({
	dataListUrl: '/product/grContactunits/page',
	queryForm: {
		param: '',
		type: props.type,
		categoryIdList: []
	},
	dataListSelections: [] // 添加 dataListSelections 属性
})

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

const handleClick = (categoryIdList: any[]) => {
	state.queryForm.categoryIdList = categoryIdList
	getDataList()
}

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

const { getDataList, selectionChangeHandle, sizeChangeHandle, currentChangeHandle } = useCrud(state)
</script>

<style scoped>
.custom-dialog {
	max-height: calc(100vh - 100px);
	/* 根据需要调整 */
	overflow-y: auto;
	/* 启用垂直滚动 */
}
</style>
