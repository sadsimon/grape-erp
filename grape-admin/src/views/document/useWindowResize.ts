import { ref, onMounted, onUnmounted } from 'vue';

export function useWindowResize(occupyHeight: number) {
  const headHeight = ref(0)
  const listHeight = ref(0)
  const footHeight = ref(0)
  
  
  const getHeadHeight = () => {
    const headElement = document.querySelector('.head.layout-query') as HTMLElement
    if (headElement) {
      headHeight.value = headElement.offsetHeight
    }
  }
  
  const isArrowUpFu = (isArrowUp: number) => {
  	footHeight.value = isArrowUp
  	updateTableHeight()
  }
  
  // 更新表格高度的方法
  const updateTableHeight = () => {
  	listHeight.value = window.innerHeight - footHeight.value -headHeight.value - occupyHeight
  }
  
  // 在组件挂载时初始化高度，并监听窗口大小变化
  onMounted(() => {
  	getHeadHeight()//获取头部高度
  	updateTableHeight() // 初始化高度
  	window.addEventListener('resize', updateTableHeight) // 监听窗口大小变化
  	window.addEventListener('resize', getHeadHeight)
  })
  
  // 在组件卸载时移除事件监听
  onUnmounted(() => {
  	window.removeEventListener('resize', updateTableHeight)
  	window.removeEventListener('resize', getHeadHeight)
  })

  return {
    headHeight,
    listHeight,
    footHeight,
    occupyHeight,
    getHeadHeight,
    updateTableHeight,
	isArrowUpFu,
  }
}