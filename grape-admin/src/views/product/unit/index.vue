<template>
	<el-space size = "small" alignment="flex-start">
		<el-card style="width: 480px;">
			<UnitSingle></UnitSingle>
		</el-card>
		<el-card style="width: 480px;">
			<UnitMultiple></UnitMultiple>
		</el-card>
	</el-space>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import UnitSingle from './single/index.vue'
import UnitMultiple from './multiple/index.vue'
</script>

<style></style>
