<template>
	<teleport v-if="props.toTeleport && visible" :to="props.toTeleport">
		<DialogContent :model-value="visible" :show-full="props.showFull" @close="handleClose" :title :width="width">
			<template v-for="(_, name) in $slots" #[name]="scope">
				<slot :name="name" v-bind="scope" />
			</template>
		</DialogContent>
	</teleport>
	<DialogContent v-else :model-value="visible" :show-full="props.showFull" @close="handleClose" :title :width="width">
		<template v-for="(_, name) in $slots" #[name]="scope">
			<slot :name="name" v-bind="scope" />
		</template>
	</DialogContent>
</template>

<script setup lang="ts" name="GrDialog">
import { defineProps, defineEmits, ref, watch, computed } from 'vue'
import { useAppStore } from '@/store/modules/app'
import DialogContent from './gr-dialog-content/index.vue'

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
	},
	title: { type: String, default: '' },
	toTeleport: { type: String, default: '' }
})

const visible = ref(props.modelValue)
const emit = defineEmits(['update:modelValue', 'close'])

const handleClose = () => {
	visible.value = false // 先更新内部状态
	emit('update:modelValue', false) // 同步到 v-model
	emit('close') // 触发 close 事件
}

// 动态计算遮罩层的类名
const dynamicModalClass = computed(() => {
	return appStore.sidebarOpened ? 'dialog_class_opened' : 'dialog_class_closed'
})


const isFullscreen = ref(false)
const toggleFullscreen = () => {
	isFullscreen.value = !isFullscreen.value
}

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

<style>
.el-dialog {
	pointer-events: auto;
}

/* 默认遮罩层样式 */
.dialog_class_opened,
.dialog_class_closed {
	/* pointer-events: none; */
	top: calc(var(--theme-header-height) + 41px);
	/* 顶部菜单高度 */
	height: calc(100% - 82px);
	/* 遮罩层高度 */
	transition:
		width 0.3s ease,
		left 0.3s ease;
	/* 添加过渡效果 */
	border-radius: 4px;
}

/* 当菜单展开时的样式 */
.dialog_class_opened {
	left: 185px;
	/* 展开时的左侧距离 */
	width: calc(100% - 190px);
	/* 展开时的宽度 */
}

/* 当菜单关闭时的样式 */
.dialog_class_closed {
	left: 69px;
	/* 关闭时的左侧距离 */
	width: calc(100% - 74px);
	/* 关闭时的宽度 */
}

.el-overlay-dialog {
	top: calc(var(--theme-header-height) + 41px);
	left: 185px;
}

.dialog-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 100%;
	padding-right: 40px;
	/* Make space for the close button */
	position: relative;
}

.header-actions {
	position: absolute;
	right: 40px;
	/* Position to the left of the close button */
	top: 7px;
	transform: translateY(-50%);
}
</style>
