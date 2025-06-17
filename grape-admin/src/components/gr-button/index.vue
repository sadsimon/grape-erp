<template>
	<el-select @change="change" v-model="selectedValue" :placeholder :disabled :style="{ width: prop.width }">
		<template #prefix>
		      <slot name="prefix"></slot>
		    </template>
		<template #default>
			<el-option v-for="item in data" :key="item[props.value]" :label="item[props.label]" :value="item[props.value]" @click="handleOptionClick()"></el-option>
		</template>
		
	</el-select>
</template>

<script setup lang="ts" name="GrButton">
import { PropType, ref, watch } from 'vue'
import { useDataApi } from '@/api/common'

// 定义选项的接口
interface Option {
  [key: string]: any; // 或者更具体的类型
  id: string | number; // 假设每个选项都有一个 id
  name: string; // 假设每个选项都有一个 name
}

interface Prop {
	value: any
	label: string
}
const prop = defineProps({
	data: {
		type: Array as PropType<Option[]>,
		default: () => []
	},
	props: {
		type: Object as PropType<Prop>,
		required: false,
		default: () => ({
			value: 'id',
			label: 'name'
		})
	},
	disabled: {
		type: Boolean,
		required: false,
		default: () => false
	},
	placeholder: {
		type: String,
		required: false,
		default: () => ''
	},
	width: {
		type: String,
		required: false,
		default: '100%'
	}
})

const emit = defineEmits(['select'])
const change = (value: any)=>{
	const selectedOption = prop.data?.find(item => item[prop.props.value] === value)
	emit('select',selectedOption)
	
}

const selectedValue = ref(null)
// 处理选项点击事件
const handleOptionClick = () => {
  selectedValue.value = null // 清除选中的值
}

</script>
<style scoped>
	:deep(.el-select__placeholder.is-transparent) {
		color: #606266;
	}
</style>
