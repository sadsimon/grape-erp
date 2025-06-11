<template>
	<el-input v-model="model" :disabled @input="handleInput" @blur="handleBlur"> </el-input>
</template>

<script setup lang="ts" name="GrNumberInput">
const props = defineProps({
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

const model = defineModel<any | any[]>()

const handleInput = (value: string) => {
	// 校验输入内容是否为合法的数字或小数
	const isValid = /^(\d+\.?\d*|\.\d+)$/.test(value)
	// 如果输入合法，则更新 model；否则还原为之前的值
	if (isValid || value === '') {
		model.value = value
	} else {
		// 非法输入：返回0
		model.value = 0
	}
}

const emit = defineEmits<{
	(e: 'blur'): void // 2. 声明blur自定义事件
}>()

const handleBlur = (value: string) => {
	emit('blur')
}
</script>

<style scoped>
::v-deep .el-input__inner {
	text-align: right;
	/* 数字靠右对齐 */
}
</style>
