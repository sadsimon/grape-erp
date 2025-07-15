<template>
	<el-upload
		:show-file-list="false"
		style="width: 100%"
		:action="uploadUrl"
		:headers="{ Authorization: cache.getToken() }"
		multiple
		:limit="limit"
		:before-upload="handleBeforeUpload"
		:on-success="handleSuccess"
		:disabled="disabled"
	>
		<el-button icon="Upload" plain> 点击上传</el-button>
	</el-upload>
</template>

<script setup lang="ts">
import { ElMessage, ElNotification, UploadProps, UploadUserFile } from 'element-plus'
import constant from '@/utils/constant'
import cache from '@/utils/cache'
import { ref, watch } from 'vue'

const props = defineProps({
	action: {
		type: String,
		required: false,
		default: () => null
	},
	size: {
		type: Number,
		default: 5
	},
	limit: {
		type: Number,
		default: 3
	},
	disabled: {
		type: Boolean,
		default: false
	}
})

// 上传URL
const uploadUrl = props.action ? constant.apiUrl + props.action : constant.uploadUrl

const model = defineModel<any>()
const fileList = ref<UploadUserFile[]>([])

const handleSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
	if (response.code !== 0) {
		ElMessage.error('上传失败：' + response.msg)
		return false
	}

	if (model.value) {
		model.value = model.value + ',' + response.data.url
	} else {
		model.value = response.data.url
	}
}

const handleBeforeUpload: UploadProps['beforeUpload'] = rawFile => {
	const fileSize = rawFile.size / 1024 / 1024 < props.size
	if (!fileSize) {
		ElNotification({
			title: '温馨提示',
			message: `文件大小不能超过 ${props.size}MB！`,
			type: 'warning'
		})
	}
	return fileSize
}

const handleExceed = () => {
	ElNotification({
		title: '温馨提示',
		message: `最大上传数量为：${props.limit}，请移除后上传！`,
		type: 'warning'
	})
}
</script>
