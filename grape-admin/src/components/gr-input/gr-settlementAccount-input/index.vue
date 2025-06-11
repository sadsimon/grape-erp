<template>
	<ma-data-select
		ref="maDataSelectRef"
		v-model="model"
		:url="'/product/grSettlementAccount/list'"
		:props="{ label: 'accountName', value: 'id' }"
		:placeholder
		filterable
		:style="{ width: width }"
		@select="selectOption"
	>
	</ma-data-select>
</template>

<script setup lang="ts" name="GrSettlementAccountInput">
import { reactive, ref } from 'vue'
interface MaDataSelect {
	refresh: () => void
}
defineProps({
	placeholder: {
		type: String,
		required: false,
		default: ''
	},
	width: {
		type: String,
		required: false,
		default: '100%'
	}
})
const maDataSelectRef = ref<MaDataSelect | null>(null) // MaDataSelect 的 ref
const model = defineModel<number | string>()

const emit = defineEmits(['select'])
const selectOption = (data: any) => {
	emit('select',data)
}

const addOrUpdateRef = ref()
const addOrUpdateHandle = (isUpdate: Boolean, row?: any) => {
	addOrUpdateRef.value.init(isUpdate, row)
}

const refreshDataList = () => {
	if (maDataSelectRef.value) {
		maDataSelectRef.value.refresh()
	} else {
		console.error('maDataSelectRef.value is null')
	}
}
</script>

<style></style>
