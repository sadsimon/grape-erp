<template>
  <el-card 
    :class="['gr-document-foot', { 'dragging': isDragging }]" 
    :style="{ height: currentHeight + 'px', transition: isDragging ? 'none' : 'height 0.2s ease' }"
  >
    <!-- 可拖拽区域 -->
    <div 
      class="drag-handle"
      @mousedown="startDrag"
    >
      <!-- 保留原有按钮和点击事件 -->
      <el-button 
        :icon="buttoncIcon" 
        size="small" 
        type="primary" 
        class="collapse-button"
        @click.stop="toggleFootHeight"
      >
        {{buttonName}}
      </el-button>
    </div>
    <slot></slot>
  </el-card>
</template>

<script setup lang="ts" name="GrDocumentFoot">
  import { ref, onMounted, onUnmounted } from 'vue'
  
  const props = defineProps({
    height: {
      type: Number,
      default: 200
    },
    minHeight: {
      type: Number,
      default: 40
    },
    maxHeight: {
      type: Number,
      default: 400
    }
  })

  const currentHeight = ref(Math.min(props.height, props.maxHeight))
  const buttonName = ref("收起")
  const buttoncIcon = ref("ArrowDown")
  const isDragging = ref(false)
  const emit = defineEmits(['height-change', 'isArrowUp'])

  // 拖拽相关逻辑
  let animationFrameId: number | null = null
  let startY = 0
  let startHeight = 0

  const startDrag = (e: MouseEvent) => {
    // 如果点击的是按钮，则不触发拖拽（保留按钮点击功能）
    if ((e.target as HTMLElement).closest('.el-button')) {
      return
    }
    
    isDragging.value = true
    startY = e.clientY
    startHeight = currentHeight.value
    document.addEventListener('mousemove', onDrag)
    document.addEventListener('mouseup', stopDrag)
    e.preventDefault()
  }

  const onDrag = (e: MouseEvent) => {
    if (!isDragging.value) return
    
    if (animationFrameId) {
      cancelAnimationFrame(animationFrameId)
    }
    
    animationFrameId = requestAnimationFrame(() => {
      const deltaY = startY - e.clientY
      let newHeight = startHeight + deltaY
      
      newHeight = Math.max(props.minHeight, Math.min(props.maxHeight, newHeight))
      
	  // 边界阻尼效果
	    /* if (newHeight >= props.maxHeight * 0.95) {
	      newHeight = props.maxHeight
	    } */
		
      currentHeight.value = newHeight
      updateButtonState(newHeight)
      emit('height-change', newHeight)
      emit('isArrowUp', newHeight)
    })
  }

  const stopDrag = () => {
    isDragging.value = false
    if (animationFrameId) {
      cancelAnimationFrame(animationFrameId)
      animationFrameId = null
    }
    document.removeEventListener('mousemove', onDrag)
    document.removeEventListener('mouseup', stopDrag)
  }

  // 保留原有切换方法==========================
  const toggleFootHeight = () => {
    if (currentHeight.value > props.minHeight) {
      currentHeight.value = props.minHeight
      //isAtMax.value = false // 新增：切换到最小高度时重置状态
    } else {
      currentHeight.value = Math.min(props.height, props.maxHeight) // 保持不超过max
      //isAtMax.value = currentHeight.value >= props.maxHeight // 新增：同步检查边界状态
    }
    updateButtonState(currentHeight.value)
    emit('height-change', currentHeight.value)
    emit('isArrowUp', currentHeight.value)
  }

  const updateButtonState = (height: number) => {
    if (height <= props.minHeight) {
      buttonName.value = "展开"
      buttoncIcon.value = "ArrowUp"
    } else {
      buttonName.value = "收起"
      buttoncIcon.value = "ArrowDown"
    }
  }

  onMounted(() => {
    updateButtonState(currentHeight.value)
    emit('isArrowUp', currentHeight.value)
  })

  onUnmounted(() => {
    stopDrag()
  })
</script>

<style scoped>
  /* 拖拽区域样式 */
  .drag-handle {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    cursor: ns-resize;
    user-select: none;
    touch-action: none;
	margin-top: -5px;
	height: 10px;
  }
  /* 交互反馈 */
  .drag-handle:hover .drag-indicator {
    opacity: 0.6;
  }
  
  .dragging .drag-indicator {
    opacity: 0.9;
    height: 5px;
  }

  /* 按钮样式 */
  .collapse-button {
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    transition: all 0.2s ease;
  }

  /* 防止按钮上的鼠标事件影响拖拽 */
  .collapse-button :deep(*) {
    pointer-events: none;
  }

  /* 卡片样式 */
  .gr-document-foot {
    border-top-left-radius: 0 !important;
    border-top-right-radius: 0 !important;
    will-change: height;
    contain: content;
  }
  
  :deep(.el-tabs__header) {
  		margin: 5px !important;
  		width: 45%;
  		height: 20px;
  		border-bottom: none !important;
  	}
  	:deep(.el-tabs__item){
  		height: 30px;
  		padding: 0 10px !important;
  	}
  	:deep(.el-tabs__item:hover) {
  	    background-color: var(--el-color-primary-light-9) !important;
  		transition: background-color 0.3s ease, color 0.3s ease;
  		
  	}
  	:deep(.el-tabs__item.is-active) {
  		background-color: var(--el-color-primary) !important;
  		color: var(--el-color-white) !important;
  		transition: background-color 0.3s ease, color 0.3s ease;
  		
  	}
	
</style>