package cn.nicecoder.longtblog.service.Impl;

import cn.hutool.db.sql.SqlBuilder;
import cn.nicecoder.longtblog.dao.ArticleDao;
import cn.nicecoder.longtblog.dao.CatalogDao;
import cn.nicecoder.longtblog.entity.Article;
import cn.nicecoder.longtblog.entity.Catalog;
import cn.nicecoder.longtblog.entity.Tag;
import cn.nicecoder.longtblog.service.ArticleService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import javax.cache.annotation.CacheResult;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @Author: longt
 * @Date: 2019/4/1 15:25
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Autowired
    CatalogDao catalogDao;

    @Override
    public Article articleCreate(Article article) {
        article  = articleDao.save(article);
        return article;
    }

    @Override
    public Page<Model> articleSearch(int pageNumber, int pageSize, String title, Long catalogId, String tagId, String status, String type) {
        Specification querySpeci = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = Lists.newArrayList();
                if(!StringUtils.isEmpty(title)) {
                    predicates.add(criteriaBuilder
                            .like(root.get("title"), "%" + title + "%"));
                }
                if(!StringUtils.isEmpty(catalogId)){
                    Catalog cat = new Catalog();
                    cat.setId(catalogId);
                    predicates.add(criteriaBuilder
                            .equal(root.get("catalog"), cat));
                }
                if(!StringUtils.isEmpty(type)){
                    predicates.add(criteriaBuilder
                            .equal(root.get("type"), type));
                }
                if(!StringUtils.isEmpty(tagId)){
                    Join<Article, Tag> articleJoin = root.join("tags", JoinType.LEFT);
                    predicates.add(criteriaBuilder.equal(articleJoin.get("id"), tagId)) ;
                }
                if(!StringUtils.isEmpty(status)){
                    predicates.add(criteriaBuilder
                            .like(root.get("status"), "%" + status + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return articleDao.findAll(querySpeci, PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "createTime"));
    }

    @Override
    public Article articleDetail(Long id) {
        Article article  = null;
        //在空的Optional实例上调用get()，抛出NoSuchElementException
        if(articleDao.findById(id) != null && articleDao.findById(id).isPresent()){
            article = articleDao.findById(id).get();
        }
        return article;
    }

    @Override
    public List<Article> articleNearly(Long id) {
        List<Article> articles = new ArrayList<Article>();
        Article pre = articleDao.findPre(id);
        if(pre == null){
            pre = new Article();
        }
        Article next = articleDao.findNext(id);
        if(next == null){
            next = new Article();
        }
        articles.add(pre);
        articles.add(next);
        return articles;
    }


    @Override
    public Catalog findCatalogById(Long id) {
        Long catalogId = articleDao.findCatalogId(id);
        return catalogDao.getOne(catalogId);
    }

    @Override
    @CacheResult(cacheName="findTop8")
    public List<Article> findTop8() {
        return articleDao.findTop8();
    }

    @Override
    public void deleteArticle(Article article) {
        articleDao.delete(article);
        if(article.getCatalog() != null) {
            catalogDao.updateCatalogCountSub(article.getCatalog().getId());
        }
    }
}
