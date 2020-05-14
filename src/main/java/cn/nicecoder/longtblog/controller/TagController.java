package cn.nicecoder.longtblog.controller;

import cn.nicecoder.longtblog.entity.Tag;
import cn.nicecoder.longtblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Page<Tag> allTags(){
        Page<Tag> tags = tagService.tagPage(0,100);
        return tags;
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public Tag detail(@PathVariable Long id){
        Tag tags = tagService.findById(id);
        return tags;
    }

    @RequestMapping(value = "getTagsById/{artId}", method = RequestMethod.GET)
    public List<Tag> getTagsById(@PathVariable Long artId){
        List<Tag> tags = tagService.findTagsById(artId);
        return tags;
    }

}
