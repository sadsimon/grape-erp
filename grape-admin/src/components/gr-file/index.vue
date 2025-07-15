<template>
	<el-button @click="visible = true">
		<ma-icon size='23' icon="icon-attachment" />
	</el-button>
	<FileAddOrUpdateDialog v-if="visible" :key="visible + ''" v-model:visible="visible" @select="userHandle"> </FileAddOrUpdateDialog>
</template>

<script setup lang="ts" name="GrFile">
	import FileAddOrUpdateDialog from './file-add-or-update.vue'
	import { useCrud } from '@/hooks'
	import { reactive, ref } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	
	const visible = ref(false)
	
	
	const userHandle =()=>{
		
	}
	const state: IHooksOptions = reactive({
		dataListUrl: '/product/grBrand/page',
		deleteUrl: '/product/grBrand',
		queryForm: {
			param: ''
		}
	})
	
	const { getDataList, sizeChangeHandle, selectionChangeHandle, sortChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
	
</script>

<style>
</style>