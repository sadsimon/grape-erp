<template>
	<GrDialog :model-value="visible" width="800px" title="附件上传" :close-on-click-modal="false" draggable @close="handleClose">
		<el-space direction="vertical">
			<el-table
				v-loading="state.dataListLoading"
				show-overflow-tooltip
				:data="state.dataList"
				border
				class="layout-table"
				@selection-change="selectionChangeHandle"
			>
				<el-table-column label="操作" header-align="center" align="center" width="90">
					<template #default="scope">
						<el-button type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
					</template>
				</el-table-column>
				<el-table-column prop="name" width="200" label="附件名称" header-align="center" align="center" />
				<el-table-column prop="size" width="150" label="附件大小" header-align="center" align="center">
					<template #default="scope">
						{{ convertSizeFormat(scope.row.size) }}
					</template>
				</el-table-column>
				<el-table-column prop="creator" width="150" label="上传人" header-align="center" align="center" />
				<el-table-column prop="createTime" width="170" label="上传时间" header-align="center" align="center" />
			</el-table>
			<div style="width:760px; display:flex; justify-content:flex-start;">
			  <FileUpload />
			</div>
			
		</el-space>
		<template #footer>
			<el-button @click="handleClose()">关闭</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts">
	import { useCrud } from '@/hooks'
	import { reactive, ref } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	import FileUpload from './file-uplod.vue'
	import { convertSizeFormat } from '@/utils/tool'
	
	const props = defineProps({
		visible: {
			type: Boolean,
			default: false
		},
		businessCode: {
			type: String,
			required: true
		}
	})
	
	const emit = defineEmits(['update:visible'])
	
	const state: IHooksOptions = reactive({
		dataListUrl: 'sys/attachment/page',
		deleteUrl: 'sys/attachment',
		queryForm: {
			businessCode: props.businessCode,
		}
	})
	
	const handleClose = () => {
		emit('update:visible', false)
	}
	const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
	
</script>

<style>
</style>