<template>
  <el-popover trigger="click" class="box-item" :width="400" placement="right">
    <template #reference>
      <el-button icon="Tools">列设置</el-button>
    </template>
    <el-row :gutter="10">
      <el-col :span="8" v-for="item in columns" :key="item.code">
        <el-checkbox v-model="model[item.code]" :label="item.name" @change="handleChange(item.code, $event)" />
      </el-col>
      <el-col :span="24">
        <el-link :underline="false" type="primary" @click="resetDefault">恢复默认</el-link>
      </el-col>
    </el-row>
  </el-popover>
</template>

<script setup lang="ts" name="GrField">
	import { ElPopover } from 'element-plus'
	import { ref, reactive, watch } from 'vue'
	import { IHooksColumn } from '@/hooks/interface'
	import { useFieldSubmitApi, useFieldRestoreDefaultApi } from '@/api/product/system'
	import { ElMessage } from 'element-plus/es'
	
	const props = defineProps<{
	  columns: IHooksColumn[]
	  columnCode: {
		  type: String
		  required: true
	  }
	}>()
	
	const model = ref<Record<string, boolean>>({})
	
	watch(
	  () => props.columns,
	  (newColumns) => {
	    if (newColumns && newColumns.length > 0) {
	      newColumns.forEach((col) => {
	        model.value[col.code] = col.check
	      })
	      emit('update:model', model.value)
	    }
	  },
	  { immediate: true, deep: true }
	)
	
	const emit = defineEmits(['update:model'])
	
	const handleChange = (code: string, event: boolean) => {
	  const isChecked = event
	  model.value[code] = isChecked
	  // 更新 columns 的 check 属性
	  const column = props.columns.find((col) => col.code === code)
	  if (column) {
	    column.check = isChecked
	  }
	  // 调用保存逻辑
	  saveColumns(props.columns)
	}
	
	/* watch(
	  () => model.value,
	  (newVal) => {
		// 更新 columns 中的 check 属性
		props.columns.forEach((col) => {
		col.check = newVal[col.code] ?? false
		})
		saveColumns(props.columns)
		emit('update:model', newVal)
	  },
	  { deep: true }
	) */
	
	const saveColumns=(columns: IHooksColumn[])=>{
		useFieldSubmitApi({'fieldJson':JSON.stringify(columns), 'code':props.columnCode}).then(() => {
			ElMessage.success({
				message: '操作成功',
				duration: 500
			})
		})
	}
	
	const resetDefault = () => {
	  useFieldRestoreDefaultApi(props.columnCode).then((res: any) => {
		if (res.data.fieldJson) {
			JSON.parse(res.data.fieldJson).forEach((col:any) => {
			  model.value[col.code] = col.check
			})
		} 
		emit('update:model', model.value)
	  })
	}
	
</script>

<style>
	
</style>