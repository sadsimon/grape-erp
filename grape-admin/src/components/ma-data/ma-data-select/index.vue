<template>
	<el-select @change="change" v-model="model" :placeholder :disabled :clearable :style="{ width: prop.width }">
		<el-option v-for="item in dataList" :key="item[props.value]" :label="item[props.label]" :value="item[props.value]"></el-option>
		<template #footer>
			<slot name="footer"></slot>
		</template>
	</el-select>
</template>

<script setup lang="ts" name="MaDataSelect">
import { PropType, ref, watch } from 'vue'
import { useDataApi } from '@/api/common'

interface Prop {
	value: any
	label: string
}
const prop = defineProps({
	url: {
		type: String,
		default: ''
	},
	data: {
		type: Array,
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
	clearable: {
		type: Boolean,
		required: false,
		default: () => true
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

watch(
	() => prop.data,
	val => {
		dataList.value = val
	},
	{ deep: true }
)

const model = defineModel<number | string>()

// 获取列表
const dataList = ref<any[]>()
const getDataList = async () => {
	if (prop.url.length === 0) {
		return
	}
	dataList.value = await useDataApi(prop.url)
}

getDataList()

const refresh = () => {
	getDataList()
}

const emit = defineEmits(['select'])
const change = (value: any)=>{
	console.log(value)
	const selectedOption = dataList.value?.find(item => item[prop.props.value] === value)
	emit('select',selectedOption)
}
// 暴露 refresh 方法
defineExpose({
	refresh
})
</script>
