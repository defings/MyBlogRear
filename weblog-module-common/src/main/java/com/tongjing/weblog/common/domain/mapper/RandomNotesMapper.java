package com.tongjing.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tongjing.weblog.common.domain.dos.RandomNotesDo;
import com.tongjing.weblog.common.domain.vo.RandomNotesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/6/18 10:54]
 * @updateUser : [TongJing]
 * @updateTime : [2024/6/18 10:54]
 * @updateRemark : [说明本次修改内容]
 */
@Mapper
public interface RandomNotesMapper extends BaseMapper<RandomNotesDo> {
    @Select("SELECT t_random_notes.*, t_user.avatar_path, t_user.username FROM t_random_notes JOIN t_user ON t_random_notes.user_id = t_user.id WHERE t_random_notes.`status` = #{status}")
    public List<RandomNotesVo> findAllRandomNotesByStatus(@Param("status") int status);
    @Select("SELECT t_random_notes.*, t_user.avatar_path, t_user.username FROM t_random_notes JOIN t_user ON t_random_notes.user_id = t_user.id")
    public List<RandomNotesVo> findAllRandomNotes();
    @Select("SELECT t_random_notes.*, t_user.avatar_path, t_user.username FROM\n" +
            "(SELECT t_random_notes.id FROM t_random_notes WHERE t_random_notes.`status` = #{status} ORDER BY t_random_notes.id LIMIT #{current}, #{size}) temp\n" +
            "LEFT JOIN t_random_notes ON t_random_notes.id = temp.id\n" +
            "JOIN t_user ON t_random_notes.user_id = t_user.id;")
    public List<RandomNotesVo> findPageRandomNotesByStatus(@Param("status") int status, @Param("current") Long current, @Param("size") Long size);

}
