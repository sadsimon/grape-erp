<template>
	<div class="stamp" v-if="showStamp">{{ props.textValue }}</div>
</template>

<script setup lang="ts" name="GrStamp">
	import { ref, watch, computed } from 'vue'
	const props = defineProps({
		isShow: {
			type: Boolean,
			default: () => true
		},
		textValue: {
			type: String,
			required: false
		}
	})
	const isShow = ref(props.isShow)
	const textValue = ref(props.textValue)
	watch(
		() => props.isShow,
		newVal => {
			isShow.value = newVal
		}
	)
	
	const showStamp = computed(() => isShow.value && textValue.value) // 控制印章显示
</script>

<style scoped>
	
	.stamp {
	  user-select: none;
	  position: absolute;
	  top: 20%; /* 垂直方向上居中 */
	  left: 40%; /* 水平方向上居中 */
	  transform: translate(-50%, -50%) rotate(-25deg); /* 居中并倾斜 */
	  background-color: transparent; /* 背景透明 */
	
	  /* 设置边框为实线且带渐变 */
	  border: 5px solid;
	  border-image: linear-gradient(to bottom right, rgba(255, 77, 79, 0.1), rgba(255, 77, 79, 0.4)) 1;
	  border-radius: 10px; /* 圆角半径 */
	
	  color: transparent; /* 文字颜色设为透明以应用背景渐变 */
	  font-size: 48px;
	  padding: 10px 20px;
	  z-index: 1000;
	
	  /* 设置文字渐变：淡红到深红 */
	  background-image: linear-gradient(to bottom right, rgba(255, 204, 204, 0.4), rgba(255, 77, 79, 0.4));
	  -webkit-background-clip: text; /* 将背景裁剪为文字形状 */
	  background-clip: text; /* 标准属性 */
	
	  /* 调整字体和边框样式以更接近图片效果 */
	  font-weight: bold;
	  text-align: center;
	  line-height: 1.5;
	  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
	}
</style>