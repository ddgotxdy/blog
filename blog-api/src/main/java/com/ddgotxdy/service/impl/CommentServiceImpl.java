package com.ddgotxdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ddgotxdy.entity.Article;
import com.ddgotxdy.entity.Comment;
import com.ddgotxdy.entity.LoginUser;
import com.ddgotxdy.entity.SysUser;
import com.ddgotxdy.mapper.ArticleMapper;
import com.ddgotxdy.mapper.CommentMapper;
import com.ddgotxdy.service.CommentService;
import com.ddgotxdy.service.SysUserService;
import com.ddgotxdy.service.ThreadService;
import com.ddgotxdy.vo.CommentParam;
import com.ddgotxdy.vo.CommentVO;
import com.ddgotxdy.vo.Result;
import com.ddgotxdy.vo.UserVO;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ddgo
 * @description: 评论服务实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ThreadService threadService;

    @Override
    public Result commentByArticleId(Long articleId) {
        // 根据文章id查询评论列表的一级列表
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Comment::getArticleId, articleId)
                .eq(Comment::getLevel, 1);
        List<Comment> commentList = commentMapper.selectList(queryWrapper);
        return Result.success(copyList(commentList));
    }

    @Override
    public Result comment(CommentParam commentParam) {
        // springSecurity 上下文获取登录用户
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = loginUser.getUser();
        // 设置评论
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        }else {
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);

        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        // 存入评论
        commentMapper.insert(comment);
        // 异步更新评论数
        Article article = articleMapper.selectById(commentParam.getArticleId());
        threadService.updateCommentCount(article);
        return Result.success(copy(comment));
    }

    private List<CommentVO> copyList(List<Comment> commentList) {
        List<CommentVO> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVO copy(Comment comment) {
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(comment, commentVO);
        // 时间格式化
        commentVO.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));
        // 获取评论用户
        Long authorId = comment.getAuthorId();
        UserVO userVO = (UserVO) sysUserService.findUserVoById(authorId).getData();
        commentVO.setAuthor(userVO);

        // 评论的评论
        List<CommentVO> commentVOList = findCommentsByParentId(comment.getId());
        commentVO.setChildrens(commentVOList);
        // 子评论的判断
        if (comment.getLevel() > 1) {
            Long toUid = comment.getToUid();
            UserVO toUserVo = (UserVO) sysUserService.findUserVoById(toUid).getData();
            commentVO.setToUser(toUserVo);
        }
        return commentVO;
    }

    private List<CommentVO> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id);
        queryWrapper.eq(Comment::getLevel,2);
        List<Comment> comments = this.commentMapper.selectList(queryWrapper);
        return copyList(comments);
    }
}
