<?php
//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》  http://lblbc.cn/blog
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
declare(strict_types=1);

namespace app\controller;

use app\model\Note;
use Exception;
use think\facade\Request;
use app\JwtUtils;


class NoteController
{
    public function list()
    {
        $authorization = Request::header('Authorization');
        $userId = JwtUtils::getUserIdFromToken($authorization);
        $data = Note::where('user_id', $userId)->select();
        return json([
            'code' => 0,
            'msg' => "",
            'data' =>  $data
        ]);
    }

    public function queryNote($noteId)
    {
        $authorization = Request::header('Authorization');
        $userId = JwtUtils::getUserIdFromToken($authorization);
        $data = Note::where('id', $noteId)->find();
        return json([
            'code' => 0,
            'msg' => "",
            'data' =>  $data
        ]);
    }

    public function addNote()
    {
        $authorization = Request::header('Authorization');
        $userId = JwtUtils::getUserIdFromToken($authorization);
        $requestData = Request::post();
        $requestData['user_id'] = $userId;
        $create = Note::create($requestData);

        return json([
            'code' => 0,
            'msg' => ""
        ]);
    }

    public function modifyNote($noteId)
    {
        $authorization = Request::header('Authorization');
        $userId = JwtUtils::getUserIdFromToken($authorization);

        $requestData = Request::post();
        $note = Note::where('id', $noteId)->find();
        $data = Request::only(['content']);
        $note->save($data);

        return json([
            'code' => 0,
            'msg' => ""
        ]);
    }

    public function deleteNote($noteId)
    {
        Note::destroy($noteId);

        return json([
            'code' => 0,
            'msg' => ""
        ]);
    }
}
