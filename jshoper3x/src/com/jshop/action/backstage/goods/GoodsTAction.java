package com.jshop.action.backstage.goods;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jshop.action.backstage.image.ImgTAction;
import com.jshop.action.backstage.modelbean.GoodsparameterlistModel;
import com.jshop.action.backstage.template.CreateHtml;
import com.jshop.action.backstage.template.DataCollectionTAction;
import com.jshop.action.backstage.tools.BaseTools;
import com.jshop.action.backstage.tools.Serial;
import com.jshop.action.backstage.tools.StaticString;
import com.jshop.action.backstage.tools.Validate;
import com.jshop.entity.GoodsAttributeRpT;
import com.jshop.entity.GoodsDetailRpT;
import com.jshop.entity.GoodsT;
import com.jshop.entity.GoodsTwocodeRelationshipT;
import com.jshop.entity.GoodsTypeTN;
import com.jshop.entity.ProductT;
import com.jshop.service.ArticleCategoryTService;
import com.jshop.service.ArticleTService;
import com.jshop.service.GoodsAttributeRpTService;
import com.jshop.service.GoodsBelinkedTService;
import com.jshop.service.GoodsCommentTService;
import com.jshop.service.GoodsDetailRpTService;
import com.jshop.service.GoodsSpecificationsRelationshipTService;
import com.jshop.service.GoodsTService;
import com.jshop.service.GoodsTwocodeRelationshipTService;
import com.jshop.service.GoodsTypeTNService;
import com.jshop.service.JshopbasicInfoTService;
import com.jshop.service.ProductTService;
import com.jshop.service.SiteNavigationTService;
import com.opensymphony.xwork2.ActionSupport;
import com.swetake.util.Qrcode;

import freemarker.template.TemplateException;
@Namespace("")
@ParentPackage("jshop")
public class GoodsTAction extends ActionSupport {
	private GoodsTService goodsTService;
	private Serial serial;
	private ProductTService productTService;
	private ImgTAction imgTAction;
	private ArticleTService articleTService;
	private ArticleCategoryTService articleCategoryTService;
	private JshopbasicInfoTService jshopbasicInfoTService;
	private SiteNavigationTService siteNavigationTService;
	private GoodsAttributeRpTService goodsAttributeRpTService;
	private GoodsDetailRpTService goodsDetailRpTService;
	private GoodsCommentTService goodsCommentTService;
	private GoodsTypeTNService goodsTypeTNService;
	private GoodsBelinkedTService goodsBelinkedTService;
	private CreateHtml createHtml;
	private DataCollectionTAction dataCollectionTAction;
	private GoodsSpecificationsRelationshipTService goodsSpecificationsRelationshipTService;
	private GoodsTwocodeRelationshipTService goodsTwocodeRelationshipTService;
	private String goodsid;
	private String goodsname;
	private String brandname;
	private boolean flag;
	private String nname;
	private String lname;
	private String sname;
	private String fname;
	private String navid;
	private String ltypeid;
	private String stypeid;
	private String price;
	private String memberprice;
	private String points;
	private String pictureurl;
	private String quantity;
	private String salestate;
	private String detail;
	private String keywordname;
	private String weight;
	private String readcount;
	private String relatedproductid;
	private String recommended;
	private String hotsale;
	private String bargainprice;
	private String sort;
	private Date createtime;
	private String creatorid;
	private String replycount;
	private String brandid;
	private String placeStore;
	private String metaKeywords;
	private String metaDescription;
	private String cost;
	private String saleprice;
	private String isNew;
	private String htmlPath;
	private String productSn;
	private String productName;
	private String unit;
	private String store;
	private String isDefault;
	private String isSalestate;
	private String warehouseLocation;
	private String goodsParameterValue;
	private String freezeStore;
	private String keywordid;
	private String productplaceid;
	private String goodsTypeId;
	private String goodsTypeName;
	private String goodsAttrsVals;
	private String usersetnum;
	private String isSpecificationsOpen;
	private String specificationsValue;
	private String specificationsid;
	private String specificationsName;
	private String star;
	private String otherPath;
	private String pathName;
	private String staruser;
	private String totalcomment;
	private String ismobileplatformgoods;
	private String commoditylist;//清单
	private String belinkedgoodsid;//关联商品id串
	private String isvirtual;//是否虚拟商品标记
	private String virtualresults;//虚拟商品返回结果
	private String rejson;
	private String query;//text
	private String qtype;//select
	private String sortname;//排序字段
	private String sortorder;//排序方式
	private GoodsT bean = new GoodsT();
	private GoodsT gt = new GoodsT();
	private List<GoodsT>beanlist=new ArrayList<GoodsT>();
	private List rows = new ArrayList();
	private Map<String, Object> map = new HashMap<String, Object>();
	private List<GoodsTypeTN>gtnlist=new ArrayList<GoodsTypeTN>();
	private GoodsTypeTN gtnbean=new GoodsTypeTN();
	private List<GoodsparameterlistModel>gmllist=new ArrayList<GoodsparameterlistModel>();
	private int rp;
	private int page = 1;
	private int total = 0;
	private File fileupload;
	private String fileuploadFileName;
	private String allfilename;
	private String pcpath;
	private String twocodepath;
	private boolean delpcflag;
	private boolean slogin;
	private boolean sucflag;

	@JSON(serialize = false)
	public GoodsDetailRpTService getGoodsDetailRpTService() {
		return goodsDetailRpTService;
	}

	public void setGoodsDetailRpTService(GoodsDetailRpTService goodsDetailRpTService) {
		this.goodsDetailRpTService = goodsDetailRpTService;
	}

	@JSON(serialize = false)
	public GoodsAttributeRpTService getGoodsAttributeRpTService() {
		return goodsAttributeRpTService;
	}

	public void setGoodsAttributeRpTService(
			GoodsAttributeRpTService goodsAttributeRpTService) {
		this.goodsAttributeRpTService = goodsAttributeRpTService;
	}

	@JSON(serialize = false)
	public GoodsTwocodeRelationshipTService getGoodsTwocodeRelationshipTService() {
		return goodsTwocodeRelationshipTService;
	}

	public void setGoodsTwocodeRelationshipTService(
			GoodsTwocodeRelationshipTService goodsTwocodeRelationshipTService) {
		this.goodsTwocodeRelationshipTService = goodsTwocodeRelationshipTService;
	}

	@JSON(serialize = false)
	public GoodsBelinkedTService getGoodsBelinkedTService() {
		return goodsBelinkedTService;
	}

	public void setGoodsBelinkedTService(GoodsBelinkedTService goodsBelinkedTService) {
		this.goodsBelinkedTService = goodsBelinkedTService;
	}

	@JSON(serialize = false)
	public GoodsTypeTNService getGoodsTypeTNService() {
		return goodsTypeTNService;
	}

	public void setGoodsTypeTNService(GoodsTypeTNService goodsTypeTNService) {
		this.goodsTypeTNService = goodsTypeTNService;
	}

	@JSON(serialize = false)
	public DataCollectionTAction getDataCollectionTAction() {
		return dataCollectionTAction;
	}

	public void setDataCollectionTAction(DataCollectionTAction dataCollectionTAction) {
		this.dataCollectionTAction = dataCollectionTAction;
	}

	@JSON(serialize = false)
	public GoodsTService getGoodsTService() {
		return goodsTService;
	}

	public void setGoodsTService(GoodsTService goodsTService) {
		this.goodsTService = goodsTService;
	}

	@JSON(serialize = false)
	public ProductTService getProductTService() {
		return productTService;
	}

	public void setProductTService(ProductTService productTService) {
		this.productTService = productTService;
	}

	@JSON(serialize = false)
	public ArticleTService getArticleTService() {
		return articleTService;
	}

	public void setArticleTService(ArticleTService articleTService) {
		this.articleTService = articleTService;
	}

	@JSON(serialize = false)
	public ArticleCategoryTService getArticleCategoryTService() {
		return articleCategoryTService;
	}

	public void setArticleCategoryTService(ArticleCategoryTService articleCategoryTService) {
		this.articleCategoryTService = articleCategoryTService;
	}

	@JSON(serialize = false)
	public JshopbasicInfoTService getJshopbasicInfoTService() {
		return jshopbasicInfoTService;
	}

	public void setJshopbasicInfoTService(JshopbasicInfoTService jshopbasicInfoTService) {
		this.jshopbasicInfoTService = jshopbasicInfoTService;
	}

	@JSON(serialize = false)
	public SiteNavigationTService getSiteNavigationTService() {
		return siteNavigationTService;
	}

	public void setSiteNavigationTService(SiteNavigationTService siteNavigationTService) {
		this.siteNavigationTService = siteNavigationTService;
	}

	@JSON(serialize = false)
	public GoodsCommentTService getGoodsCommentTService() {
		return goodsCommentTService;
	}

	public void setGoodsCommentTService(GoodsCommentTService goodsCommentTService) {
		this.goodsCommentTService = goodsCommentTService;
	}
	
	@JSON(serialize = false)
	public GoodsSpecificationsRelationshipTService getGoodsSpecificationsRelationshipTService() {
		return goodsSpecificationsRelationshipTService;
	}

	public void setGoodsSpecificationsRelationshipTService(
			GoodsSpecificationsRelationshipTService goodsSpecificationsRelationshipTService) {
		this.goodsSpecificationsRelationshipTService = goodsSpecificationsRelationshipTService;
	}

	@JSON(serialize = false)
	public Serial getSerial() {
		return serial;
	}

	public void setSerial(Serial serial) {
		this.serial = serial;
	}

	@JSON(serialize = false)
	public ImgTAction getImgTAction() {
		return imgTAction;
	}

	public void setImgTAction(ImgTAction imgTAction) {
		this.imgTAction = imgTAction;
	}

	@JSON(serialize = false)
	public CreateHtml getCreateHtml() {
		return createHtml;
	}

	public void setCreateHtml(CreateHtml createHtml) {
		this.createHtml = createHtml;
	}

	public String getTwocodepath() {
		return twocodepath;
	}

	public void setTwocodepath(String twocodepath) {
		this.twocodepath = twocodepath;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}


	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getNavid() {
		return navid;
	}

	public void setNavid(String navid) {
		this.navid = navid;
	}

	public String getLtypeid() {
		return ltypeid;
	}

	public void setLtypeid(String ltypeid) {
		this.ltypeid = ltypeid;
	}

	public String getStypeid() {
		return stypeid;
	}

	public void setStypeid(String stypeid) {
		this.stypeid = stypeid;
	}

	public String getPictureurl() {
		return pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	public String getSalestate() {
		return salestate;
	}

	public void setSalestate(String salestate) {
		this.salestate = salestate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getKeywordname() {
		return keywordname;
	}

	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRelatedproductid() {
		return relatedproductid;
	}

	public void setRelatedproductid(String relatedproductid) {
		this.relatedproductid = relatedproductid;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	public String getHotsale() {
		return hotsale;
	}

	public void setHotsale(String hotsale) {
		this.hotsale = hotsale;
	}

	public String getBargainprice() {
		return bargainprice;
	}

	public void setBargainprice(String bargainprice) {
		this.bargainprice = bargainprice;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getPlaceStore() {
		return placeStore;
	}

	public void setPlaceStore(String placeStore) {
		this.placeStore = placeStore;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	
	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getGoodsParameterValue() {
		return goodsParameterValue;
	}

	public void setGoodsParameterValue(String goodsParameterValue) {
		this.goodsParameterValue = goodsParameterValue;
	}

	public String getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(String keywordid) {
		this.keywordid = keywordid;
	}

	public String getProductplaceid() {
		return productplaceid;
	}

	public void setProductplaceid(String productplaceid) {
		this.productplaceid = productplaceid;
	}

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}


	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public GoodsT getBean() {
		return bean;
	}

	public void setBean(GoodsT bean) {
		this.bean = bean;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	public String getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public String getAllfilename() {
		return allfilename;
	}

	public void setAllfilename(String allfilename) {
		this.allfilename = allfilename;
	}

	public String getPcpath() {
		return pcpath;
	}

	public void setPcpath(String pcpath) {
		this.pcpath = pcpath;
	}

	public boolean isDelpcflag() {
		return delpcflag;
	}

	public void setDelpcflag(boolean delpcflag) {
		this.delpcflag = delpcflag;
	}

	public boolean isSlogin() {
		return slogin;
	}

	public void setSlogin(boolean slogin) {
		this.slogin = slogin;
	}

	public boolean isSucflag() {
		return sucflag;
	}

	public void setSucflag(boolean sucflag) {
		this.sucflag = sucflag;
	}

	public String getUsersetnum() {
		return usersetnum;
	}

	public void setUsersetnum(String usersetnum) {
		this.usersetnum = usersetnum;
	}

	public String getIsSpecificationsOpen() {
		return isSpecificationsOpen;
	}

	public void setIsSpecificationsOpen(String isSpecificationsOpen) {
		this.isSpecificationsOpen = isSpecificationsOpen;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMemberprice() {
		return memberprice;
	}

	public void setMemberprice(String memberprice) {
		this.memberprice = memberprice;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getWarehouseLocation() {
		return warehouseLocation;
	}

	public void setWarehouseLocation(String warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getReadcount() {
		return readcount;
	}

	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getReplycount() {
		return replycount;
	}

	public void setReplycount(String replycount) {
		this.replycount = replycount;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}

	public String getFreezeStore() {
		return freezeStore;
	}

	public void setFreezeStore(String freezeStore) {
		this.freezeStore = freezeStore;
	}

	public String getRejson() {
		return rejson;
	}

	public void setRejson(String rejson) {
		this.rejson = rejson;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getStaruser() {
		return staruser;
	}

	public void setStaruser(String staruser) {
		this.staruser = staruser;
	}

	public String getTotalcomment() {
		return totalcomment;
	}

	public void setTotalcomment(String totalcomment) {
		this.totalcomment = totalcomment;
	}

	public String getIsmobileplatformgoods() {
		return ismobileplatformgoods;
	}

	public void setIsmobileplatformgoods(String ismobileplatformgoods) {
		this.ismobileplatformgoods = ismobileplatformgoods;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getSpecificationsValue() {
		return specificationsValue;
	}

	public void setSpecificationsValue(String specificationsValue) {
		this.specificationsValue = specificationsValue;
	}



	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getGoodsAttrsVals() {
		return goodsAttrsVals;
	}

	public void setGoodsAttrsVals(String goodsAttrsVals) {
		this.goodsAttrsVals = goodsAttrsVals;
	}

	public GoodsT getGt() {
		return gt;
	}

	public void setGt(GoodsT gt) {
		this.gt = gt;
	}

	public List<GoodsTypeTN> getGtnlist() {
		return gtnlist;
	}

	public void setGtnlist(List<GoodsTypeTN> gtnlist) {
		this.gtnlist = gtnlist;
	}

	public GoodsTypeTN getGtnbean() {
		return gtnbean;
	}

	public void setGtnbean(GoodsTypeTN gtnbean) {
		this.gtnbean = gtnbean;
	}

	public List<GoodsparameterlistModel> getGmllist() {
		return gmllist;
	}

	public void setGmllist(List<GoodsparameterlistModel> gmllist) {
		this.gmllist = gmllist;
	}
	
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getCommoditylist() {
		return commoditylist;
	}

	public void setCommoditylist(String commoditylist) {
		this.commoditylist = commoditylist;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getSpecificationsid() {
		return specificationsid;
	}

	public void setSpecificationsid(String specificationsid) {
		this.specificationsid = specificationsid;
	}

	public String getSpecificationsName() {
		return specificationsName;
	}

	public void setSpecificationsName(String specificationsName) {
		this.specificationsName = specificationsName;
	}

	public List<GoodsT> getBeanlist() {
		return beanlist;
	}

	public void setBeanlist(List<GoodsT> beanlist) {
		this.beanlist = beanlist;
	}

	public String getBelinkedgoodsid() {
		return belinkedgoodsid;
	}

	public void setBelinkedgoodsid(String belinkedgoodsid) {
		this.belinkedgoodsid = belinkedgoodsid;
	}

	public String getIsvirtual() {
		return isvirtual;
	}

	public void setIsvirtual(String isvirtual) {
		this.isvirtual = isvirtual;
	}

	public String getVirtualresults() {
		return virtualresults;
	}

	public void setVirtualresults(String virtualresults) {
		this.virtualresults = virtualresults;
	}


	public String getIsSalestate() {
		return isSalestate;
	}

	public void setIsSalestate(String isSalestate) {
		this.isSalestate = isSalestate;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getOtherPath() {
		return otherPath;
	}

	public void setOtherPath(String otherPath) {
		this.otherPath = otherPath;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}

	/**
	 * 增加商品
	 * 
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@Action(value = "saveGoods", results = { @Result(name = "json", type = "json") })
	public String saveGoods()  {
		GoodsT gt=new GoodsT();
		gt.setGoodsid(this.getSerial().Serialid(Serial.GOODS));
		gt.setGoodsTypeId(this.getGoodsTypeId());
		gt.setGoodsTypeName(this.getGoodsTypeName());
		gt.setGoodsParameterValue(this.getGoodsParameterValue());
		gt.setNavid(this.getNavid());
		gt.setNname(this.getNname());
		gt.setLtypeid(this.getLtypeid());
		gt.setLname(this.getLname());
		gt.setStypeid(this.getStypeid());
		gt.setSname(this.getSname());
		gt.setFname(this.getFname());
		gt.setGoodsname(this.getGoodsname());
		gt.setUsersetnum(this.getUsersetnum());
		gt.setBrandid(this.getBrandid());
		gt.setBrandname(this.getBrandname());
		gt.setPoints(Double.parseDouble(this.getPoints()));
		gt.setSort(Integer.parseInt(this.getSort()));
		gt.setIsNew(this.getIsNew());
		gt.setRecommended(this.getRecommended());
		gt.setHotsale(this.getHotsale());
		gt.setBargainprice(this.getBargainprice());
		gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
		gt.setSalestate(this.getSalestate());
		gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
		gt.setPictureurl(this.getPictureurl());
		gt.setCommoditylist(this.getCommoditylist());
		gt.setMetaDescription(this.getMetaDescription());
		gt.setMetaKeywords(this.getMetaKeywords());
		this.getGoodsTService().saveGoods(gt);
		//保存属性
		saveGoodsAttributeRpT(gt.getGoodsid());
		//保存商品介绍
		saveGoodsDetail(gt.getGoodsid());
		//保存货物信息
		saveProductT();
		this.setSucflag(true);
		return "json";
	}

	/**
	 * 保存货物信息
	 */
	private void saveProductT(){
		ProductT pt=new ProductT();
		pt.setProductid(this.getSerial().Serialid(Serial.PRODUCT));
		pt.setGoodsid(gt.getGoodsid());
		pt.setProductName(this.getProductName());
		pt.setProductSn(this.getProductSn());
		pt.setCost(Double.parseDouble(this.getCost()));
		pt.setSaleprice(Double.parseDouble(this.getSaleprice()));
		pt.setMemberprice(Double.parseDouble(this.getMemberprice()));
		pt.setPrice(Double.parseDouble(this.getPrice()));
		pt.setWeight(this.getWeight());
		pt.setUnit(this.getUnit());
		pt.setStore(Integer.parseInt(this.getStore()));
		pt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
		pt.setWarehouseLocation(this.getWarehouseLocation());
		pt.setPlaceStore(this.getPlaceStore());
		pt.setIsDefault(this.getIsDefault());
		pt.setIsSalestate(this.getIsSalestate());
		pt.setSpecificationsValue(this.getSpecificationsValue());
		pt.setSpecificationsName(this.getSpecificationsName());
		pt.setSpecificationsid(this.getSpecificationsid());
		pt.setCreatetime(BaseTools.systemtime());
		pt.setCreatorid(BaseTools.adminCreateId());
		pt.setUpdatetime(pt.getCreatetime());
		this.getProductTService().saveProductT(pt);
	}
	
	/**
	 * 插入商品属性到属性和商品关系表
	 * @param goodsid
	 */
	private void saveGoodsAttributeRpT(String goodsid){
		JSONArray ja=(JSONArray)JSONValue.parse(this.getGoodsAttrsVals());
		int jsonsize=ja.size();
		GoodsAttributeRpT gart=new GoodsAttributeRpT();
		for(int i=0;i<jsonsize;i++){
			gart.setId(this.getSerial().Serialid(Serial.GOODSATTRIBUTERPT));
			gart.setGoodsid(goodsid);
			JSONObject jo=(JSONObject) ja.get(i);
			gart.setAttrval(jo.get(StaticString.ATTRVAL).toString());
			this.getGoodsAttributeRpTService().saveGoodsAttributeRpT(gart);
		}
		
	}
	/**
	 * 保存商品介绍
	 * @param goodsid
	 */
	private void saveGoodsDetail(String goodsid){
		GoodsDetailRpT gdpt=new GoodsDetailRpT();
		gdpt.setId(this.getSerial().Serialid(Serial.GOODSDETAILRPT));
		gdpt.setDetail(this.getDetail());
		gdpt.setGoodsid(goodsid);
		this.getGoodsDetailRpTService().saveGoodsDetailRpT(gdpt);
	}
	
	/**
	 * 根据用户id读取所有该用户增加的商品
	 * 
	 * @return
	 */
	@Action(value = "findAllGoods", results = { @Result(name = "json", type = "json", params = { "excludeNullProperties", "true" }) })
	public String findAllGoods() {
		if ("sc".equals(this.getQtype())) {
			finddefaultAllGoods();

		} else {
			if (Validate.StrisNull(this.getQtype())) {
				return "json";
			} else {
				if (this.getQtype().equals("goodsname")) {
					//findGoodsByGoodsname();
				}
			}
		}
		return "json";

	}

	/**
	 * 获取用户的所有商品信息
	 */
	private void finddefaultAllGoods() {
		int currentPage = page;
		int lineSize = rp;
		if (Validate.StrNotNull(sortname) && Validate.StrNotNull(sortorder)) {
			String queryString = "from GoodsT as gt where gt.creatorid=:creatorid order by " + sortname + " " + sortorder + "";
			List<GoodsT> list = this.getGoodsTService().sortAllGoods(currentPage, lineSize, BaseTools.adminCreateId(), queryString);
			if (!list.isEmpty()) {
				ProcessGoodsList(list);
			}
		}
	}

	/**
	 * 迭代处理供前台显示
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void ProcessGoodsList(List<GoodsT> list) {
		total = this.getGoodsTService().countAllGoods(BaseTools.adminCreateId());
		rows.clear();
		for (Iterator it = list.iterator(); it.hasNext();) {
			GoodsT gt = (GoodsT) it.next();
			if (gt.getRecommended().equals("1")) {
				gt.setRecommended("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setRecommended("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			if (gt.getHotsale().equals("1")) {
				gt.setHotsale("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setHotsale("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			if (gt.getBargainprice().equals("1")) {
				gt.setBargainprice("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setBargainprice("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			if (gt.getIsNew().equals("1")) {
				gt.setIsNew("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setIsNew("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			if (gt.getSalestate().equals("1")) {
				gt.setSalestate("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setSalestate("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}

			Map cellMap = new HashMap();
			cellMap.put("id", gt.getGoodsid());
			cellMap.put("cell", new Object[] { gt.getGoodsname(), gt.getUsersetnum(), gt.getMemberprice(),gt.getNname(),  gt.getSalestate(), gt.getIsNew(), gt.getBargainprice(), gt.getHotsale(), gt.getRecommended(), gt.getQuantity(), "<a target='_blank' id='editgoods' href='editgoods.jsp?' name='editgoods'>[编辑]</a>" + "<a target='_blank' id='browergoods' href='" + gt.getHtmlPath() + "' name='browergoods'>[预览]</a>" });
			rows.add(cellMap);
		}
	}

	/**
	 * 根据商品id获取商品数据
	 * 
	 * @return
	 */
	@Action(value = "findGoodsById", results = { @Result(name = "json", type = "json") })
	public String findGoodsById() {

		if (Validate.StrNotNull(this.getGoodsid())) {
			bean = this.getGoodsTService().findGoodsById(this.getGoodsid().trim());
			if (bean != null) {
				//this.setBasepath(BaseTools.getBasePath());
				this.setSucflag(true);
				return "json";
			}
		}
		this.setSucflag(false);
		return "json";

	}

	/**
	 * 更新文章静态路径
	 * 
	 * @param articleid
	 * @param htmlPath
	 */
	public void updateHtmlPath(String goodsid, String htmlPath) {
		this.getGoodsTService().updateHtmlPath(goodsid, htmlPath);
	}

	/**
	 * 更新商品
	 * 
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@Action(value = "updateGoods", results = { @Result(name = "json", type = "json") })
	public String updateGoods() throws IOException, TemplateException {
		return allfilename;
		
	}

	/**
	 * 删除商品同时删除商品对应的货品
	 * 
	 * @return
	 */
	@Action(value = "delGoods", results = { @Result(name = "json", type = "json") })
	//判断是否上架
	public String delGoods() {
		if (Validate.StrNotNull(this.getGoodsid())) {
			String[] strs = this.getGoodsid().split(",");
			for (int i = 0; i < strs.length; i++) {
				this.getProductTService().delProductTBygoodsid(strs[i], BaseTools.adminCreateId());
			}
			int i = this.getGoodsTService().delGoods(strs, BaseTools.adminCreateId());
			this.setSucflag(true);
			return "json";
		} else {
			this.setSucflag(false);
			return "json";
		}
	}

	/**
	 * 根据goodsid更新上下架状态
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsSaleState", results = { @Result(name = "json", type = "json") })
	public String updateGoodsSaleState() {
		if (Validate.StrNotNull(this.getGoodsid())) {
			String[] strs = this.getGoodsid().split(",");
			int i = this.getGoodsTService().updateGoodsSaleState(strs, this.getSalestate(), BaseTools.adminCreateId());
			this.setSucflag(true);
			return "json";
		} else {
			this.setSucflag(false);
			return "json";
		}
	}

	/**
	 * 根据goodsid更新特价商品
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsbargainprice", results = { @Result(name = "json", type = "json") })
	public String updateGoodsbargainprice() {
		if (Validate.StrNotNull(this.getGoodsid())) {
			String[] strs = this.getGoodsid().split(",");
			int i = this.getGoodsTService().updateGoodsbargainprice(strs, this.getBargainprice(), BaseTools.adminCreateId());
			this.setSucflag(true);
			return "json";
		} else {
			this.setSucflag(false);
			return "json";
		}

	}

	/**
	 * 根据goodsid更新热销商品
	 * 
	 * @return
	 */
	@Action(value = "updateGoodshotsale", results = { @Result(name = "json", type = "json") })
	public String updateGoodshotsale() {
		if (Validate.StrNotNull(this.getGoodsid())) {
			String[] strs = this.getGoodsid().split(",");
			int i = this.getGoodsTService().updateGoodshotsale(strs, this.getHotsale(), BaseTools.adminCreateId());
			this.setSucflag(true);
			return "json";
		} else {
			this.setSucflag(false);
			return "json";
		}
	}

	/**
	 * 根据goodsid更新推荐商品
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsrecommended", results = { @Result(name = "json", type = "json") })
	public String updateGoodsrecommended() {
		if (Validate.StrNotNull(this.getGoodsid())) {
			String[] strs = this.getGoodsid().split(",");
			int i = this.getGoodsTService().updateGoodsrecommended(strs, this.getRecommended(), BaseTools.adminCreateId());
			this.setSucflag(true);
			return "json";
		} else {
			this.setSucflag(false);
			return "json";
		}
	}

	/**
	 * 根据goodsid更新新品商品
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsisNew", results = { @Result(name = "json", type = "json") })
	public String updateGoodsisNew() {
		if (Validate.StrNotNull(this.getGoodsid())) {
			String[] strs = this.getGoodsid().split(",");
			int i = this.getGoodsTService().updateGoodsisNew(strs, this.getIsNew(), BaseTools.adminCreateId());
			this.setSucflag(true);
			return "json";
		} else {
			this.setSucflag(false);
			return "json";
		}
	}

	/**
	 * 根据商品id更新是否同步到移动平台
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsismobileplatformgoods", results = { @Result(name = "json", type = "json") })
	public String updateGoodsismobileplatformgoods() {
		if (Validate.StrNotNull(this.getGoodsid())) {
			String[] strs = this.getGoodsid().split(",");
			int i = this.getGoodsTService().updateGoodsismobileplatformgoods(strs, this.getIsmobileplatformgoods(), BaseTools.adminCreateId());
			this.setSucflag(true);
			return "json";
		} else {
			this.setSucflag(false);
			return "json";
		}
	}

	/**
	 * 根据商品id更新5种商品状态
	 * 
	 * @return
	 */
	@Action(value = "updateFiveGoodsState", results = { @Result(name = "json", type = "json") })
	public String updateFiveGoodsState() {

		if (Validate.StrNotNull(this.getGoodsid())) {
			String[] strs = this.getGoodsid().split(",");
			int i = this.getGoodsTService().updateFiveGoodsState(strs, this.getRecommended(), this.getHotsale(), this.getBargainprice(), this.getIsNew(), this.getIsmobileplatformgoods());
			this.setSucflag(true);
			return "json";
		} else {
			this.setSucflag(false);
			return "json";
		}

	}

	/**
	 * 增加星级评分
	 * 
	 * @return
	 */
	@Action(value = "updatestarsumBygoodsid", results = { @Result(name = "json", type = "json") })
	public String updatestarsumBygoodsid() {
		this.getGoodsTService().updatestarsumBygoodsid(this.getGoodsid().trim(), Integer.parseInt(this.getStar()));
		this.setSucflag(true);
		return "json";
	}

	/**
	 * 更新商品总打分人数
	 * 
	 * @return
	 */
	@Action(value = "updatestarusersumBygoodsid", results = { @Result(name = "json", type = "json") })
	public String updatestarusersumBygoodsid() {
		this.getGoodsTService().updatestarusersumBygoodsid(this.getGoodsid(), Integer.parseInt(this.getStaruser()));
		this.setSucflag(true);
		return "json";
	}

	/**
	 * 更新商品总评分人数
	 * 
	 * @return
	 */
	@Action(value = "updatecommentsumBygoodsid", results = { @Result(name = "json", type = "json") })
	public String updatecommentsumBygoodsid() {
		this.getGoodsTService().updatecommentsumBygoodsid(this.getGoodsid(), Integer.parseInt(this.getTotalcomment()));
		this.setSucflag(true);
		return "json";
	}
	
	/**
	 * 在这里增加商品参数的处理逻辑
	 * 1,根据goodstypeid获取goodstypen中的参数列表json格式
	 * 2,迭代此json和goods中的goodsparmeters对比
	 * 3,放入一个工具bean中
	 *
	 */
	public List<GoodsparameterlistModel> processGoodsparameters(GoodsT goodst){
		List<GoodsparameterlistModel>gmllist=new ArrayList<GoodsparameterlistModel>();
		gtnlist=this.getGoodsTypeTNService().findGoodsTypeTNById(goodst.getGoodsTypeId());
		if(!gtnlist.isEmpty()){
			gtnbean=gtnlist.get(0);
			gmllist.clear();
			if(Validate.StrNotNull(gtnbean.getGoodsParameter())){
				
				JSONArray ja=(JSONArray) JSONValue.parse(gtnbean.getGoodsParameter());
				for (int i = 0; i < ja.size(); i++) {
					GoodsparameterlistModel gml=new GoodsparameterlistModel();
					JSONObject jo = (JSONObject) ja.get(i);
					gml.setGoodsTypeParamid(jo.get("id").toString());
					gml.setParamName(jo.get("name").toString());
					gml.setSortList(jo.get("sortList").toString());
					compareGoodsparameters(gmllist,gml,goodst.getGoodsParameterValue(),gml.getGoodsTypeParamid());
				}
				
			}
		}
		return gmllist;
		
		
	}
	/**
	 * 处理商品表中的商品参数
	 * @param id
	 * @param goodst
	 */
	private void compareGoodsparameters(List<GoodsparameterlistModel>gmllist,GoodsparameterlistModel gml,String goodsParameterValue,String GoodsTypeParamid){
		if(Validate.StrNotNull(goodsParameterValue)){
			JSONArray ja=(JSONArray) JSONValue.parse("["+goodsParameterValue+"]");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jo = (JSONObject) ja.get(i);
				if(jo.get("id").toString().equals(GoodsTypeParamid)){
					gml.setGoodsParamid(jo.get("id").toString());
					gml.setParamValue(jo.get("value").toString());
				}
			}
			gmllist.add(gml);
		}
	
	}
	
	/**
	 * 根据顶级分类获取商品列表传送到前台给关联商品部分
	 * @return
	 */
	@Action(value = "findAllGoodsBelinkedGoods", results = { @Result(name = "json", type = "json") })
	public String findAllGoodsBelinkedGoods(){
		if(!"0".equals(this.getNavid())&&"0".equals(this.getLtypeid())&&"0".equals(this.getStypeid())){
			String navid=this.getNavid().trim();
			String salestate="1";//上架
			String isSpecificationsOpen="0";//未开启规格
			List<GoodsT>list=this.getGoodsTService().findAllGoodsBynavid(navid, salestate,isSpecificationsOpen);
			if(!list.isEmpty()){
				this.setBeanlist(list);
			}
			this.setSucflag(true);
			return "json";
		}
		if(!"0".equals(this.getNavid())&&!"0".equals(this.getLtypeid())&&"0".equals(this.getStypeid())){
			String navid=this.getNavid().trim();
			String ltypeid=this.getLtypeid().trim();
			String salestate="1";//上架
			String isSpecificationsOpen="0";//未开启规格
			List<GoodsT>list=this.getGoodsTService().findAllGoodsBynavidandltypeid(navid, ltypeid, salestate, isSpecificationsOpen);
			if(!list.isEmpty()){
				this.setBeanlist(list);
			}
			this.setSucflag(true);
			return "json";
		}
		if(!"0".equals(this.getNavid())&&!"0".equals(this.getLtypeid())&&!"0".equals(this.getStypeid())){
			String navid=this.getNavid().trim();
			String ltypeid=this.getLtypeid().trim();
			String stypeid=this.getStypeid().trim();
			String salestate="1";//上架
			String isSpecificationsOpen="0";//未开启规格
			List<GoodsT>list=this.getGoodsTService().findAllGoodsBynavidandltypeidandstypeid(navid, ltypeid, stypeid, salestate, isSpecificationsOpen);
			if(!list.isEmpty()){
				this.setBeanlist(list);
			}
			this.setSucflag(true);
			return "json";
		}
		this.setSucflag(false);
		return "json";
	}
	
	/**
	 * 生成商品静态路径二维码
	 * @return
	 * @throws IOException
	 */
	@Action(value="encoderQRcode",results={@Result(name="json",type="json")})
	public String encoderQRcode() throws IOException{
		GoodsT goods=new GoodsT();
		Qrcode qr =new Qrcode();
		qr.setQrcodeErrorCorrect('M');
		qr.setQrcodeEncodeMode('B');
		qr.setQrcodeVersion(7);
		BufferedImage bufImg= new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufImg.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, 140,140);
		  // 设定图像颜色 > BLACK  
		gs.setColor(Color.BLACK);
		// 设置偏移量 不设置可能导致解析出错
		int pixoff=2;
		byte[] htmlPath;
		 // 根据商品id获取商品数据
			if (Validate.StrNotNull(this.getGoodsid())) {
				goods = this.getGoodsTService().findGoodsById(this.getGoodsid().trim());
				
				if (goods != null) {					
					HttpServletRequest requet=ServletActionContext.getRequest();
					String Path="http://"+requet.getRemoteAddr()+"/"+ goods.getHtmlPath();
					 htmlPath=Path.getBytes("utf-8");
					 // 输出内容 > 二维码  
					if(htmlPath.length>0 && htmlPath.length<120){
						boolean[][] codeOut=qr.calQrcode(htmlPath);
						for(int i=0;i<codeOut.length;i++){
							for(int j=0;j<codeOut.length;j++){
								if(codeOut[j][i]){
									gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
								}
							}
						}
					}
					gs.dispose();
					bufImg.flush();
					String jshoppath=ServletActionContext.getServletContext().getRealPath("");//获取根目录
					String path=jshoppath+isexistdir();
					//根目录路径
					String codePath=path+goods.getGoodsid()+".png";
					//文件夹路径
					String code = isexistdir()+goods.getGoodsid()+".png";
					//生成二维码图片名称
					File imgFile= new File(codePath);
					// 生成二维码QRCode图片
					ImageIO.write(bufImg, "png", imgFile);
					GoodsTwocodeRelationshipT list = this.getGoodsTwocodeRelationshipTService().findGoodsQRCodeByGoodsid(goods.getGoodsid());
					GoodsTwocodeRelationshipT goodscode = new GoodsTwocodeRelationshipT();
					//当数据里面存在此记录的时候，只修改二维码路径
					if(list!=null){						
						this.getGoodsTwocodeRelationshipTService().updateGoodsQRCode(goods.getGoodsid(), code);
						this.setFlag(true);
						return "json";
					}else{
						//生成商品与二维码关系的记录
						
						goodscode.setGoodsname(goods.getGoodsname());
						goodscode.setGoodsid(goods.getGoodsid());
						goodscode.setId(this.getSerial().Serialid(Serial.GOODSQRCODE));
						goodscode.setState("1");
						goodscode.setTwocodepath(code);
						this.getGoodsTwocodeRelationshipTService().addGoodsQRCode(goodscode);
						
						this.setFlag(true);
						return "json";
					}
				}
				
			}else{
				htmlPath=this.getOtherPath().getBytes("utf-8");	
					 // 输出内容 > 二维码  
					if(htmlPath.length>0 && htmlPath.length<120){
						boolean[][] codeOut=qr.calQrcode(htmlPath);
						for(int i=0;i<codeOut.length;i++){
							for(int j=0;j<codeOut.length;j++){
								if(codeOut[j][i]){
									gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
								}
							}
						}
				}
				gs.dispose();
				bufImg.flush();
				String jshoppath=ServletActionContext.getServletContext().getRealPath("");//获取根目录
				String path=jshoppath+isexistdir();
				//生成二维码图片名称
				File imgFile= new File(path+this.getPathName()+".png");
				// 生成二维码QRCode图片
				ImageIO.write(bufImg, "png", imgFile);
				
				this.setFlag(true);
				return "json";
			}
			return "json";
	}
	/**
	 * 检测目录是否存在
	 * 
	 * @return
	 */

	public static String isexistdir() {
		String nowTimeStr = "";
		String savedir = "/QRcode/";
		String realpath = "";
//		SimpleDateFormat sDateFormat;
//		sDateFormat = new SimpleDateFormat("yyyyMMdd");
//		nowTimeStr = sDateFormat.format(new Date());
		String savePath = ServletActionContext.getServletContext().getRealPath("");
		savePath = savePath + savedir ;
		File dir = new File(savePath);
		if (!dir.exists()) {
			dir.mkdirs();
			realpath = savedir ;
			return realpath;
		} else {
			realpath = savedir ;
			return realpath;
		}
	}
	
	
	
}
