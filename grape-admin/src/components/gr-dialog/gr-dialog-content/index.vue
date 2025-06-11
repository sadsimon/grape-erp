<template>
	<el-dialog
		v-bind="$attrs"
		v-model="visible"
		:close-on-click-modal="false"
		:modal-class="dynamicModalClass"
		:fullscreen="isFullscreen"
		:width="width"
		close-icon="CloseBold"
	>
		<template #header>
			<div class="dialog-header">
				<slot name="header"></slot>
				<div class="header-title">
					<el-text class="mx-1" size="large">{{ $attrs.title }}</el-text>
				</div>
				<div class="header-actions">
					<el-button v-if="props.showFull" type="text" @click="toggleFullscreen">
						<ma-icon color="var(--el-color-info)" :icon="isFullscreen ? 'icon-compress' : 'icon-expend'" />
					</el-button>
				</div>
			</div>
		</template>
		<template #default>
			<slot></slot>
		</template>
		<template #footer>
			<slot name="footer"></slot>
		</template>
	</el-dialog>
</template>

<script setup lang="ts" name="GrDialog">
import { defineProps, defineEmits, ref, watch, computed } from 'vue'
import { useAppStore } from '@/store/modules/app'

const appStore = useAppStore()

const props = defineProps({
	modelValue: {
		type: Boolean,
		default: false
	},
	showFull: {
		type: Boolean,
		default: false
	},
	width: {
		type: String,
		default: '70%'
	}
})

// 动态计算遮罩层的类名
const dynamicModalClass = computed(() => {
	return appStore.sidebarOpened ? 'dialog_class_opened' : 'dialog_class_closed'
})

const emit = defineEmits(['update:modelValue'])

const isFullscreen = ref(false)
const toggleFullscreen = () => {
	isFullscreen.value = !isFullscreen.value
}

const visible = ref(props.modelValue)
watch(
	() => props.modelValue,
	newVal => {
		visible.value = newVal
	}
)

watch(visible, newVal => {
	emit('update:modelValue', newVal)
})
</script>
