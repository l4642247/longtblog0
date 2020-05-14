package cn.nicecoder.longtblog.controller;

import cn.nicecoder.longtblog.entity.Article;
import cn.nicecoder.longtblog.entity.Catalog;
import cn.nicecoder.longtblog.entity.Tag;
import cn.nicecoder.longtblog.service.ArticleService;
import cn.nicecoder.longtblog.service.CatalogService;
import cn.nicecoder.longtblog.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * @Author: longt
 * @Date: 2019/3/29 10:41
 * @Description: 核心控制器
 */
@Controller
public class PageController {
    @Autowired
    CatalogService catalogService;

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("redirect:index.html");
        mv.addObject("catalog",null);
        return mv;
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }


    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "currentPage",defaultValue = "0") int pageNumber,
                              @RequestParam(value = "pagesize",defaultValue = "5") int pageSize,
                              @RequestParam(value = "title",required = false) String title,
                              @RequestParam(value = "catalogId",required = false) Long catalogId,
                              @RequestParam(value = "tag",required = false) String tag,
                              @RequestParam(value = "status",required = false) String status){
        Page<Model> articles =  articleService.articleSearch(pageNumber, pageSize, title, catalogId, tag, status, "1");
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("articles", articles);
        mv.addObject("catalog",catalogId);
        return mv;
    }

    @RequestMapping(value = "/about.html", method = RequestMethod.GET)
    public ModelAndView about(){
        Page<Model> articles =  articleService.articleSearch(0, 1, null, null, null, null, "2");
        ModelAndView mv = new ModelAndView("about");
        if(articles.getContent().size() > 0) {
            mv.addObject("article", articles.getContent().get(0));
        }
        return mv;
    }

    @RequestMapping(value = "/gbook.html", method = RequestMethod.GET)
    public ModelAndView gbook(){
        return new ModelAndView("gbook");
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ModelAndView info(@PathVariable Long id ,
                             HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView("info");
        Article article = articleService.articleDetail(id);
        mv.addObject("catalog",articleService.findCatalogById(article.getId()));
        String content = new String(article.getContent());
        content = content.replace("<pre>","<pre><code class='language-js'>");
        content = content.replace("</pre>","</code></pre>");
        article.setContent(content.getBytes());
        mv.addObject("article",article);
        String username = IPUtil.getIpAddress(request);
        mv.addObject("username",username);
        return mv;
    }

    @RequestMapping(value = "/infopic.html", method = RequestMethod.GET)
    public ModelAndView infopic(){
        return new ModelAndView("infopic");
    }

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "currentPage",defaultValue = "0") int pageNumber,
                             @RequestParam(value = "pagesize",defaultValue = "20") int pageSize,
                             @RequestParam(value = "type",required = false) String type){
        Page<Model> articles =  articleService.articleSearch(pageNumber, pageSize, null, null, null, null, "0");
        ModelAndView mv= new ModelAndView("list");
        mv.addObject("articles", articles);
        return mv;
    }

    @RequestMapping(value = "/share.html", method = RequestMethod.GET)
    public ModelAndView share(){
        return new ModelAndView("share");
    }
}
