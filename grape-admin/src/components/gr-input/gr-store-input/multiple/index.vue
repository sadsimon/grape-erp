<template>
	<ma-data-select
		ref="maDataSelectRef"
		v-model="model"
		:url="'/product/grStore/listOfEffective'"
		:props="{ label: 'storeName', value: 'id' }"
		:placeholder
		filterable
		:style="{ width: width }"
		:disabled
		multiple
	>
		
	</ma-data-select>
</template>

<script setup lang="ts" name="GrStoreMultipleInput">
import { reactive, ref } from 'vue'

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
	},
	disabled: {
		type: Boolean,
		required: false,
		default: () => false
	},
})
const model = defineModel<number | string>()
</script>

<style></style>
