<template>
	<el-input v-model="addressInputf" :style="{ width: width }" :disabled="props.disabled" readonly>
		<template #suffix>
			<!-- 自定义清除按钮 -->
			<el-icon v-if="props.clearable" class="clear-icon" style="cursor: pointer" @click="handleClear">
				<Close />
			</el-icon>
		</template>
		<template #append>
			<el-button icon="Search" @click="handleOpen"></el-button>
		</template>
	</el-input>

	<GrDialog
		:model-value="visible"
		title="联系地址"
		:close-on-click-modal="false"
		width="800"
		draggable
		:append-to-body="true"
		@close="handleClose"
	>
		<el-row>
			<el-form>
				<el-col>
					<el-form-item prop="selectedAddress" label="省/市/区">
						<ma-address v-model="selectedAddress" width="300px"></ma-address>
					</el-form-item>
				</el-col>
				<el-col>
					<el-form-item prop="addressInfo" label="详细地址">
						<el-input v-model="addressInfo" :rows="4" style="width: 540px" type="textarea" placeholder="详细地址" />
					</el-form-item>
				</el-col>
			</el-form>
		</el-row>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts" name="GrAddressInput">
import { ref, watch, reactive, PropType, computed, onMounted } from 'vue'
import { AddressObjInt } from './index'

const visible = ref(false)
const addressInfo = ref('')
const selectedAddress = ref<string>('')

const props = defineProps({
	clearable: {
		type: Boolean,
		required: false,
		default: true
	},
	disabled: {
		type: Boolean,
		required: false,
		default: false
	},
	width: {
		type: String,
		required: false,
		default: '100%'
	},
	province: {
		type: String,
		default: ''
	},
	city: {
		type: String,
		default: ''
	},
	district: {
		type: String,
		default: ''
	},
	address: {
		type: String,
		default: ''
	}
})

const addressIntact = reactive<AddressObjInt>({
	province: props.province,
	city: props.city,
	district: props.district,
	address: props.address
})

const emit = defineEmits(['submit'])

watch(
	() => props.province,
	newValue => {
		addressIntact.province = newValue
	}
)
watch(
	() => props.city,
	newValue => {
		addressIntact.city = newValue
	}
)
watch(
	() => props.district,
	newValue => {
		addressIntact.district = newValue
	}
)
watch(
	() => props.address,
	newValue => {
		addressIntact.address = newValue
	}
)

// addressInput
const addressInputf = computed(() => {
	// 动态拼接地址
	const parts = [addressIntact.province, addressIntact.city, addressIntact.district, addressIntact.address].filter(Boolean) // 过滤掉空值
	return parts.join(',')
})

const handleOpen = () => {
	visible.value = true
	selectedAddress.value = [addressIntact.province || '', addressIntact.city || '', addressIntact.district || ''].join(',')
	addressInfo.value = addressIntact.address || ''
}

const handleClose = () => {
	visible.value = false
	selectedAddress.value = ''
	addressInfo.value = ''
}

const submitHandle = () => {
	if (selectedAddress.value !== ',,') {
		const [province, city, district] = selectedAddress.value.split(',')
		addressIntact.province = province
		addressIntact.city = city
		addressIntact.district = district
	} else {
		addressIntact.province = ''
		addressIntact.city = ''
		addressIntact.district = ''
	}

	addressIntact.address = addressInfo.value || ''

	emit('submit', addressIntact)
	visible.value = false
}

const handleClear = () => {
	console.log('删除方法')
	addressIntact.province = ''
	addressIntact.city = ''
	addressIntact.district = ''
	addressIntact.address = ''
}
</script>

<style></style>
