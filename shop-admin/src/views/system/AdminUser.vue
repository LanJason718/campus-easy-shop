<template>
  <div>
    <base-dialog
      title="ddd"
      :visible="dialog.visible"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <template #content>
        <div>内容</div>
      </template>
    </base-dialog>
    <el-button @click="onShow">打开</el-button>
  </div>
</template>

<script setup lang="ts">
import BaseDialog from '@/components/BaseDialog.vue'
import useDialog from '@/hooks/useDialog'
const { dialog, onShow, onClose, onConfirm } = useDialog()
</script>

<style lang="scss" scoped></style>
