<template>
	<div class="navbar-right">
		<a href="https://gitee.com/crazy-code/grape-erp" target="_blank">
			<ma-icon icon="icon-gitee-fill-round"></ma-icon>
		</a>
		<Search />
		<User />
		<Fullscreen />
	</div>
</template>

<script setup lang="ts">
import Lang from './components/Lang.vue'
import Search from './components/Search.vue'
import ComponentSize from './components/ComponentSize.vue'
import Fullscreen from './components/Fullscreen.vue'
import User from './components/User.vue'
</script>

<style lang="scss" scoped>
.navbar-right {
	display: flex;
	align-items: center;
	justify-content: flex-end;
	&-link {
		height: 100%;
		display: flex;
		align-items: center;
		white-space: nowrap;
		&-photo {
			width: 25px;
			height: 25px;
			border-radius: 100%;
		}
	}
}
</style>
