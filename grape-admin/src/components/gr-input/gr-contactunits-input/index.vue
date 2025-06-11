<template>
	<el-input v-model="contactunitsName" class="input-with-search" :clearable :disabled :placeholder :style="{ width: width }" @clear="model = ''">
		<template #append>
			<el-button icon="Search" @click="visible = true"></el-button>
		</template>
	</el-input>
	<GrContactunitsDialog v-if="!disabled && visible" :key="visible + ''" v-model="visible" :multiple @select="userHandle"> </GrContactunitsDialog>
</template>

<script setup lang="ts" name="GrContactunitsInput">
import { ref, watch } from 'vue'
import GrContactunitsDialog from './gr-contactunits-dialog/index.vue'
import { useContactunitsNameListApi } from '@/api/product/contactunits'

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
	width: {
		type: String,
		required: false,
		default: '100%'
	}
})

const model = defineModel<any | any[]>()
const visible = ref(false)
const contactunitsName = ref()

watch(
	() => model.value,
	async val => {
		if (val) {
			const idList = props.multiple ? val : [val]
			if (idList.length > 0) {
				const { data } = await useContactunitsNameListApi(idList)
				contactunitsName.value = data.join(',')
			}
		} else {
			contactunitsName.value = ''
		}
	},
	{
		immediate: true
	}
)

const emit = defineEmits(['select'])
const userHandle = (rows: any[]) => {
	if (props.multiple) {
		model.value = rows.map((item: any) => item.id)
	} else {
		model.value = rows[0].id
	}
	emit('select', rows)
}
</script>
<style>
::deep .input-with-search .el-input-group__append {
	background-color: var(--el-fill-color-blank);
}
</style>
