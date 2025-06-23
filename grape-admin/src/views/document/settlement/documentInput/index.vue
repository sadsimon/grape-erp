<template>
	<el-autocomplete :key="visible + ''" class="input-with-search" v-model="documentCode" :disabled :clearable :trigger-on-focus="false" :placeholder
		@select="handleSelect" @clear="documentCode = ''" @blur="handleInput"
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
	<DocumentDialog v-if="visible" :key="visible + ''" v-model:visible="visible" :to-teleport="toTeleport" :multiple
		@select="userHandle"></DocumentDialog>
</template>

<script setup lang="ts">
	import { ref, watch } from 'vue'
	import DocumentDialog from './documentDialog.vue'

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

	const documentCode = ref<any | any[]>(props.inputValue)
	const visible = ref(false)

	const handleSelect = (item : Record<string, any>) => {
		if (item.isHint) {
		    return
		  }
		userHandle([item.item]) // 单选时也传递数组，保持一致性
	}

	const handleInput = () => {
		documentCode.value = props.inputValue
	}

	const emit = defineEmits(['select'])

	const userHandle = (rows : any[]) => {
		if (props.multiple) {
			documentCode.value = rows.map((item : any) => item.documentCode)
		} else {
			documentCode.value = rows[0].userHandle
		}
		emit('select', rows)
	}
</script>

<style>
	
</style>