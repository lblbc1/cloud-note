 <!--
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 -->
<template>
  <van-nav-bar
    title="编辑"
    left-text="返回"
    right-text="删除"
    left-arrow
    @click-left="onClickLeft"
    @click-right="onClickRight"
  />
  <van-cell-group class="inputs">
    <van-field
      v-model="content"
      autosize
      type="textarea"
      class="input-content"
      placeholder="请输入正文"
      size="large"
    />
  </van-cell-group>
  <div class="bottom-wrapper">
    <van-button
      @click="doModifyNote"
      class="confirm-btn"
      type="primary"
      size="large"
      >保存</van-button
    >
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { modifyNote, queryNote, deleteNote } from '@/api/api'
import { Toast } from 'vant'

const route = useRoute()
const router = useRouter()
const noteId = ref('')
const content = ref('')

const onClickLeft = () => history.back()
const onClickRight = () => doDeleteNote()

const queryData = async (noteId) => {
  const result = await queryNote(noteId)
  content.value = result.data.content
}

const doModifyNote = async () => {
  var params = {
    content: content.value
  }
  const resp = await modifyNote(noteId.value, params)
  if (resp.code === 0) {
    router.push('/note')
  } else {
    Toast.fail(resp.msg)
  }
}

const doDeleteNote = async () => {
  const resp = await deleteNote(noteId.value)
  if (resp.code === 0) {
    router.push('/note')
  } else {
    Toast.fail(resp.msg)
  }
}

onMounted(() => {
  noteId.value = route.query.id.toString()
  queryData(noteId.value)
})

defineExpose({
  content
})
</script>

<style scoped>
.inputs {
  padding: 0 5px;
  display: flex;
  flex-direction: column;
  height: 100%;
}
.input-content {
  flex: 1;
  width: 100%;
  padding: 0 10px;
}
.bottom-wrapper {
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  margin-bottom: 30px;
}

.confirm-btn {
  width: 200px;
  height: 40px;
  border-radius: 2rem;
  background-color: #418df9;
  color: #fff;
}
</style>