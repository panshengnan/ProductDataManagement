package com.cgwx.dao;

import com.cgwx.data.dto.AdvanceProductPart1Info;
import com.cgwx.data.dto.ProductQueryList;
import com.cgwx.data.dto.ThemeticProductListByGeos;
import com.cgwx.data.entity.PdmProductInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PdmProductInfoMapper {
    int insert(PdmProductInfo record);

    List<PdmProductInfo> selectAll();
    //
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


        @Select({"SELECT client_name\nFROM pdm_product_info\nWHERE client_name like  '%${clientName}%' order by client_name collate \"C\" "})
        List<String> selectClientNameList(@Param("clientName") String var1);

        @Select({"SELECT deliver_name\nFROM pdm_product_info\nWHERE deliver_name like  '%${deliverName}%' order by deliver_name collate \"C\" "})
        List<String> selectDeliverNameList(@Param("deliverName") String var1);

        @Select({"SELECT producer\nFROM pdm_product_info\nWHERE producer like  '%${producer}%' order by producer collate \"C\" "})
        List<String> selectProducerList(@Param("producer") String var1);

        @Select({"SELECT product_type\n" +
                " FROM pdm_product_info\n" +
                "WHERE product_id = #{productId}"
        })
        int selectProductTypeByProductId(@Param("productId") String productId);

        @Select({"SELECT product_description\n" +
                " FROM pdm_product_info\n" +
                "WHERE product_id = #{productId}"
        })
        String selectProductDescriptionByProductId(@Param("productId") String productId);

        @Select("<script>"
                +"SELECT  r.product_type, r.product_description, r.product_name, r.product_id, i.industry\n" +
                "FROM pdm_product_info  r , pdm_themetic_product_info i \n" +
                "WHERE  1=1 and r.product_id =i.product_id \n" +
                "<if test='null!= productName &amp; !\"\".equals(productName)'>"
                + "and r.product_name like CONCAT('%',#{productName},'%') "
                + "</if>"
                +"<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
                + "and r.product_description like CONCAT('%',#{productDescription},'%') "
                + "</if>"
                +"<if test='\"submitTimeAsc\".equals(orderby)'>"
                + "order by r.gmt_created asc"
                + "</if>"
                +"<if test='\"submitTimeDesc\".equals(orderby)'>"
                + "order by r.gmt_created desc"
                + "</if>"
                +"<if test='null == orderby | \"\".equals(orderby)'>"
                + "order by r.gmt_created desc"
                + "</if>"
                +"</script>")

        @Results({@Result(
                column = "product_name",
                property = "productName"
        ), @Result(
                column = "product_description",
                property = "productDescription"
        ), @Result(
                column = "product_id",
                property = "productId"
        ), @Result(
                column = "industry",
                property = "industry"
        ),@Result(
                column = "product_type",
                property = "productType"
        )})
        List<ProductQueryList> selectThemeticProductByCondition(@Param("productName") String productName, @Param("productDescription") String productDescription, @Param("orderby") String orderby);


        @Select("<script>"
                +"SELECT  r.product_type, r.product_description, r.product_name, r.product_id\n" +
                "FROM pdm_product_info  r , pdm_ortho_product_info i \n" +
                "WHERE  1=1 and r.product_id =i.product_id \n" +
                "<if test='null!= productName &amp; !\"\".equals(productName)'>"
                + "and r.product_name like CONCAT('%',#{productName},'%') "
                + "</if>"
                +"<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
                + "and r.product_description like CONCAT('%',#{productDescription},'%') "
                + "</if>"
                +"<if test='\"submitTimeAsc\".equals(orderby)'>"
                + "order by r.gmt_created asc"
                + "</if>"
                +"<if test='\"submitTimeDesc\".equals(orderby)'>"
                + "order by r.gmt_created desc"
                + "</if>"
                +"<if test='null == orderby | \"\".equals(orderby)'>"
                + "order by r.gmt_created desc"
                + "</if>"
                +"</script>")

        @Results({@Result(
                column = "product_name",
                property = "productName"
        ), @Result(
                column = "product_description",
                property = "productDescription"
        ), @Result(
                column = "product_id",
                property = "productId"
        ),@Result(
                column = "product_type",
                property = "productType"
        )})
        List<ProductQueryList> selectOrthoProductByCondition(@Param("productName") String productName, @Param("productDescription") String productDescription, @Param("orderby") String orderby);


        @Select("<script>"
                +"SELECT  r.product_type, r.product_description, r.product_name, r.product_id\n" +
                "FROM pdm_product_info  r , pdm_inlay_product_info i \n" +
                "WHERE  1=1 and r.product_id =i.product_id \n" +
                "<if test='null!= productName &amp; !\"\".equals(productName)'>"
                + "and r.product_name like CONCAT('%',#{productName},'%') "
                + "</if>"
                +"<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
                + "and r.product_description like CONCAT('%',#{productDescription},'%') "
                + "</if>"
                +"<if test='\"submitTimeAsc\".equals(orderby)'>"
                + "order by r.gmt_created asc"
                + "</if>"
                +"<if test='\"submitTimeDesc\".equals(orderby)'>"
                + "order by r.gmt_created desc"
                + "</if>"
                +"<if test='null == orderby | \"\".equals(orderby)'>"
                + "order by r.gmt_created desc"
                + "</if>"
                +"</script>")

        @Results({@Result(
                column = "product_name",
                property = "productName"
        ), @Result(
                column = "product_description",
                property = "productDescription"
        ), @Result(
                column = "product_id",
                property = "productId"
        ),@Result(
                column = "product_type",
                property = "productType"
        )})
        List<ProductQueryList> selectInlayProductByCondition(@Param("productName") String productName, @Param("productDescription") String productDescription, @Param("orderby") String orderby);

        @Select("<script>"
                +"SELECT  r.product_type, r.product_description, r.product_name, r.product_id, i.industry\n" +
                "FROM pdm_product_info  r , pdm_subdivision_product_info i \n" +
                "WHERE  1=1 and r.product_id =i.product_id \n" +
                "<if test='null!= productName &amp; !\"\".equals(productName)'>"
                + "and r.product_name like CONCAT('%',#{productName},'%') "
                + "</if>"
                +"<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
                + "and r.product_description like CONCAT('%',#{productDescription},'%') "
                + "</if>"
                +"<if test='\"submitTimeAsc\".equals(orderby)'>"
                + "order by r.gmt_created asc"
                + "</if>"
                +"<if test='\"submitTimeDesc\".equals(orderby)'>"
                + "order by r.gmt_created desc"
                + "</if>"
                +"<if test='null == orderby | \"\".equals(orderby)'>"
                + "order by r.gmt_created desc"
                + "</if>"
                +"</script>")

        @Results({@Result(
                column = "product_name",
                property = "productName"
        ), @Result(
                column = "product_description",
                property = "productDescription"
        ), @Result(
                column = "product_id",
                property = "productId"
        ), @Result(
                column = "industry",
                property = "industry"
        ),@Result(
                column = "product_type",
                property = "productType"
        )})
        List<ProductQueryList> selectSubdivisionProductByCondition(@Param("productName") String productName, @Param("productDescription") String productDescription, @Param("orderby") String orderby);

        @Select("<script>"
                +"SELECT product_id \n"+
                "FROM pdm_product_info \n"+
                "WHERE 1=1 \n"+
                "<if test='null!= client_name &amp; !\"\".equals(client_name)'>"
                + "and client_name like CONCAT('%',#{client_name},'%')"
                + "</if>"
                + "<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
                + "and product_description like CONCAT('%',#{productDescription},'%')"
                + "</if>"
                +"</script>")
        List<String> getProductIdlistByclientanddescription(@Param("client_name")String client_name,@Param("productDescription")String description);

        @Select("<script>"
                +"SELECT product_name \n"+
                "FROM pdm_product_info \n"+
                "WHERE 1=1 \n"+
                "<if test='null!=product_id  &amp; !\"\".equals(product_id)'>"
                + "and product_id=#{product_id}"
                + "</if>"
                +"</script>")

        String getProductNameById(@Param("product_id")String productId);


        @Select({"SELECT product_id, product_name, \n" +
                "          client_name, deliver_name,deliver_time\n" +
                "            FROM pdm_product_info\n" +
                "            WHERE product_id = #{productId}"
        })
        @Results({@Result(
                column = "product_id",
                property = "productId"
        ), @Result(
                column = "product_name",
                property = "productName"
        ),  @Result(
                column = "client_name",
                property = "clientName"
        ), @Result(
                column = "deliver_name",
                property = "deliverName"
        ), @Result(
                column = "deliver_time",
                property = "deliverTime"
        )
        })
            //查询请求详细信息调用的数据库语句
        PdmProductInfo selectProductDetailPart1ByProductId(@Param("productId") String productId);
        @Select({"SELECT deliver_method,produce_area, \n" +
                "          produce_time, deliver_name,deliver_time\n" +
                "            FROM pdm_product_info\n" +
                "            WHERE product_id = #{productId}"
        })
        @Results({  @Result(
                column = "deliver_name",
                property = "deliverName"
        ),@Result(
                column = "deliver_time",
                property = "deliverTime"
        ),@Result(
                column = "deliver_method",
                property = "deliverMethod"
        ),@Result(
                column = "produce_time",
                property = "produceTime"
        ),@Result(
                column = "produce_area",
                property = "procucerArea"
        )
        })
            //查询请求详细信息调用的数据库语句
        AdvanceProductPart1Info selectAdvanceProductPart1ByProductId(@Param("productId") String productId);


    @Select({"SELECT deliver_method\n" +
            " FROM pdm_product_info\n" +
            "WHERE deliver_method like '%'  order by deliver_method collate \"C\" "
    })
    List<String> getDeliverMethodList();


    @Select({"SELECT produce_area\n" +
            " FROM pdm_product_info\n" +
            "WHERE produce_area like '%'  order by produce_area collate \"C\" "
    })
    List<String> getProduceAreaList();



    @Select("<script>"
            +"SELECT product_id,product_name \n"+
            "FROM pdm_product_info \n"+
            "WHERE 1=1 \n"+
            "<if test='null!= client_name &amp; !\"\".equals(client_name)'>"
            + "and client_name =#{client_name}"
            + "</if>"
            + "<if test='null!= productDescription &amp; !\"\".equals(productDescription)'>"
            + "and product_description like CONCAT('%',#{productDescription},'%')"
            + "</if>"
            +"and product_id IN ("
                +"SELECT product_id\n"
                +"            FROM pdm_themetic_product_detail_industry_info\n"
                +            "            WHERE ${where}"
                +")"
            +"and product_id IN ("
                +"SELECT product_id\n"
                +            "            FROM pdm_themetic_product_detail_info\n"
                +            "            WHERE 1=1 \n"
                +"<if test='null!= producer &amp; !\"\".equals(producer)'>"
                + "and producer =#{producer}"
                +"</if>"
                +"<if test='null!=image_geo '>"
                +"and  st_disjoint(st_geomfromgeojson(st_asgeojson(image_geo)),st_geomfromgeojson(#{image_geo}))=false"
                +"</if>"
                +")"
            +"</script>")
    @Results({  @Result(
            column = "product_id",
            property = "productId"
    ),@Result(
            column = "product_name",
            property = "productName"
    )
    })
    List<ThemeticProductListByGeos> getThemeticInfoByConditions(@Param("client_name")String client_name,
                                                                @Param("productDescription")String description,
                                                                @Param("where")String where,
                                                                @Param("image_geo")Object image_geo,
                                                                @Param("producer")String producer);

    }