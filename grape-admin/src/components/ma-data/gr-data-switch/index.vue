<template>
	<el-switch
		v-model="model"
		:active-value="1"
		:inactive-value="0"
		:active-icon="Check"
		:inactive-icon="Close"
		inline-prompt
		@update:model-value="changeState"
	/>
</template>

<script lang="ts" setup name="GrDataSwitch">
import { ref } from 'vue'
import { stateHandle } from '@/api/common'
import { Check, Close } from '@element-plus/icons-vue'

const props = defineProps({
	url: {
		type: String,
		required: true
	},
	id: {
		type: Number,
		required: true
	}
})
const changeState = (newValue: any) => {
	stateHandle(props.id, newValue, props.url)
}

const model = defineModel<number | string>()
</script>
