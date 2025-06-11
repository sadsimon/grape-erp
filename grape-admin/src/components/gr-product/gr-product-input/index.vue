<template>
	<div class="product-input">
		<el-autocomplete :key="visible + ''" class="input-with-search" v-model="barcode" :disabled :clearable :trigger-on-focus="false" :placeholder
			:fetch-suggestions="querySearch" @select="handleSelect" @clear="barcode = ''" @blur="handleInput"
			:debounce="200">
			<template #loading>
				<svg class="circular" viewBox="0 0 50 50">
					<circle class="path" cx="25" cy="25" r="20" fill="none" />
				</svg>
			</template>
			<template #append>
				<el-button :disabled icon="MoreFilled" @click="visible = true"></el-button>
			</template>
		</el-autocomplete>
		<GrProductDialog v-if="visible" :key="visible + ''" v-model:visible="visible" :to-teleport="toTeleport" :multiple
			@select="userHandle"></GrProductDialog>
	</div>
</template>

<script setup lang="ts" name="GrProductInput">
	import { ref, watch } from 'vue'
	import GrProductDialog from './gr-product-dialog/index.vue'
	import { useListByBarcodeApi } from '@/api/product/product'

	const props = defineProps({
		multiple: {
			type: Boolean,
			required: false,
			default: false
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
		toTeleport: {
			type: String,
			required: false
		},
		inputValue: {
			type: String,
			required: false
		}
	})

	const barcode = ref<any | any[]>(props.inputValue)
	const visible = ref(false)

	const querySearch = (query : string, callback : any) => {
		useListByBarcodeApi(query).then((res : any) => {
			if (res.data && res.data.length > 0) {
				const formattedData = res.data.map((item : any) => ({
					value: `${item.barcode}: ${item.name}: (${item.unitId})`,
					item, // 保留整条数据
				}))
				callback(formattedData)
			}else{
				callback([{ value: "未找到匹配的结果", isHint: true }])
			}
		})
	}

	const handleSelect = (item : Record<string, any>) => {
		if (item.isHint) {
		    return
		  }
		userHandle([item.item]) // 单选时也传递数组，保持一致性
	}

	const handleInput = () => {
		barcode.value = props.inputValue
	}

	const emit = defineEmits(['select'])

	const userHandle = (rows : any[]) => {
		if (props.multiple) {
			barcode.value = rows.map((item : any) => item.barcode)
		} else {
			barcode.value = rows[0].barcode
		}
		emit('select', rows)
	}
</script>

<style scoped>
	:deep(.el-input-group__append){
		padding: 0 8px !important;
	}
</style>