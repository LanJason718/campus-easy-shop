<template>
  <div>
    <el-form :model="searchForm" :inline="true" size="default">
      <el-form-item label="姓名">
        <el-input
          v-model="searchForm.nickname"
          placeholder="请输入姓名"
          clearable
          style="width: 200px"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="Search" @click="searchCilck">搜索</el-button>

        <el-button type="primary" icon="Plus" @click="onShow">新增</el-button>
      </el-form-item>
    </el-form>

    <base-dialog
      title="新增管理员"
      :visible="dialog.visible"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <el-form
        ref="adminFromRef"
        :model="adminModel"
        :rules="rules"
        size="default"
      >
        <el-form-item prop="username" label="账号">
          <el-input v-model="adminModel.username"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input v-model="adminModel.password"></el-input>
        </el-form-item>
        <el-form-item prop="nickname" label="姓名">
          <el-input v-model="adminModel.nickname"></el-input>
        </el-form-item>
        <el-form-item prop="sex" label="性别">
          <el-radio-group v-model="adminModel.sex">
            <el-radio :label="'0'">男</el-radio>
            <el-radio :label="'1'">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="phone" label="电话">
          <el-input v-model="adminModel.phone"></el-input>
        </el-form-item>
        <el-form-item prop="status" label="状态">
          <el-radio-group v-model="adminModel.status">
            <el-radio :label="'0'">启用</el-radio>
            <el-radio :label="'1'">停用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </base-dialog>
  </div>
</template>

<script setup lang="ts">
import BaseDialog from '@/components/BaseDialog.vue'
import useDialog from '@/hooks/useDialog'
import { reactive, ref } from 'vue'
import type { Admin } from '@/model/admin'
import type { FormInstance, FormRules } from 'element-plus'

const { dialog, onShow, onClose } = useDialog()

const searchForm = reactive({ nickname: '' })
const searchCilck = () => {}

const adminFromRef = ref<FormInstance>()

const adminModel = reactive<Admin>({
  userId: '',
  username: '',
  password: '',
  nickname: '',
  phone: '',
  sex: '',
  status: '',
})
const rules = reactive<FormRules>({
  username: [{ required: true, message: '请输入账号' }],
  password: [{ required: true, message: '请输入登录密码', trigger: 'blur' }],
  nickname: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  phone: [{ required: true, message: '请输入电话', trigger: 'blur' }],
  sex: [{ required: true, message: '请输入性别', trigger: 'blur' }],
  status: [{ required: true, message: '请输入状态', trigger: 'blur' }],
})
const onConfirm = () => {
  adminFromRef.value?.validate((valid: Boolean) => {
    if (valid) {
      dialog.visible = false
    }
  })
}
</script>

<style lang="scss" scoped></style>
