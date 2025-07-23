<template>
	<el-button @click="visible = true" :disabled>
		<ma-icon size='23' icon="icon-attachment" />
	</el-button>
	<FileAddOrUpdateDialog v-if="visible" :key="visible + ''" v-model:visible="visible" :businessCode> </FileAddOrUpdateDialog>
</template>

<script setup lang="ts" name="GrFile">
	import FileAddOrUpdateDialog from './file-add-or-update.vue'
	import { useCrud } from '@/hooks'
	import { reactive, ref, watch } from 'vue'
	import { IHooksOptions } from '@/hooks/interface'
	
	const visible = ref(false)
	const disabled = ref(false)
	
	const props = defineProps({
		businessCode: {
			type: String,
			required: true
		},
		disabled: {
			type: Boolean,
			required: false,
			default: () => false
		}
	})
	
	watch(
		() => props.disabled,
		newValue => {
			disabled.value = newValue
			console.log(props.disabled)
		}
	)
</script>

<style>
</style>