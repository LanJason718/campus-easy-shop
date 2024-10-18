<template>
  <el-dialog
    :title="title"
    :model-value="visible"
    :before-close="onClose"
    align-center
    :close-on-click-modal="false"
    :width="width + 'px'"
  >
    <div class="container">
      <slot></slot>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="onClose">取消</el-button>
        <el-button type="primary" @click="onConfirm">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup lang="ts">
interface DialogProps {
  title: string
  visible: boolean
  width: number
}
const props = withDefaults(defineProps<DialogProps>(), {
  title: '标题',
  visible: false,
})
const emit = defineEmits(['onClose', 'onConfirm'])
//定义弹框的关闭
const onClose = () => {
  emit('onClose')
}
//定义弹框的确定
const onConfirm = () => {
  emit('onConfirm')
}
</script>
