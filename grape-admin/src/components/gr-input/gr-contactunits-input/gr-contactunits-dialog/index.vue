<template>
	<ma-data-table
		:key="visible + ''"
		v-model:visible="visible"
		title="往来单位选择"
		url="/product/grContactunits/page"
		:multiple
		:columns="[
			{ field: 'categoryName', label: '类别' },
			{ field: 'contactunitsCode', label: '编号' },
			{ field: 'contactunitsName', label: '名称' },
			{ field: 'contactName', label: '联系人' },
			{ field: 'mobile', label: '手机' }
		]"
		:query-params="[
			{ field: 'categoryId', label: '类别' },
			{ field: 'param', label: '按编号/名称/联系人/电话查询', width: '400px' }
		]"
		@select="onSelect"
	></ma-data-table>
</template>

<script setup lang="ts">
defineProps({
	multiple: {
		type: Boolean,
		required: false,
		default: false
	}
})

const visible = defineModel<boolean>()

const emit = defineEmits(['select'])
const onSelect = (rows: any[]) => {
	emit('select', rows)
}
</script>
