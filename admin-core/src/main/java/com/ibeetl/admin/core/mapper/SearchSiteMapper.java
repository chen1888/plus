package com.ibeetl.admin.core.mapper;

import com.ibeetl.admin.core.web.vo.SearchSite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

public interface SearchSiteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_site
     *
     * @mbg.generated Wed Nov 21 16:01:33 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_site
     *
     * @mbg.generated Wed Nov 21 16:01:33 CST 2018
     */
    int insert(SearchSite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_site
     *
     * @mbg.generated Wed Nov 21 16:01:33 CST 2018
     */
    @Select("select * from search_site where id=#{id}")
    SearchSite selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_site
     *
     * @mbg.generated Wed Nov 21 16:01:33 CST 2018
     */
    List<SearchSite> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_site
     *
     * @mbg.generated Wed Nov 21 16:01:33 CST 2018
     */
    int updateByPrimaryKey(SearchSite record);

    @Select("SELECT * FROM search_site WHERE website = #{website}")
    List<SearchSite> findbyWebsite(String website);

    @Select({"<script>"
            ,"SELECT * FROM search_site"
            ,"<where> 1=1"
            ,"<if test='website!=null'>"
            , "and website like concat('%',#{website},'%')"
            , "</if>"
            ,"</where>"
            ,"limit #{start,jdbcType=INTEGER},#{size,jdbcType=INTEGER}"
            ,"</script>"})
    List<SearchSite> findPageList(String website,Integer start,Integer size);


    @Insert(" insert into search_site(website,domain,method,url,keywords,create_time,update_time,pageSize,pageNum) values (#{website,jdbcType=VARCHAR}, #{domain,jdbcType=VARCHAR}, #{method,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{keywords,jdbcType=VARCHAR}, #{createTime,jdbcType=DATE}, #{updateTime,jdbcType=DATE}, #{pagesize,jdbcType=VARCHAR}, #{pagenum,jdbcType=VARCHAR})")
    public int insertSite(SearchSite searchSite) throws SQLException;

    @Delete({"<script>"
            ,"delete from search_site where 1=1 and id in "
            ,"<foreach collection='list'  item='id' open='(' separator=',' close=')'  >"
            ,"#{id}"
            ,"</foreach>"
            ,"</script>"})
    public int deleteSite(List<Integer> ids) throws SQLException;
}