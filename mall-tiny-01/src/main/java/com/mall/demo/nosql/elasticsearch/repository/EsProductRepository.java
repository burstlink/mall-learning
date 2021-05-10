package com.mall.demo.nosql.elasticsearch.repository;

import com.mall.demo.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
    /**
     *
     * @param name  商品名称
     * @param subTitle 商品标题
     * @param keywords 商品关键字
     * @param pageable 分页信息
     * @return 查询结果
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable pageable);
}
