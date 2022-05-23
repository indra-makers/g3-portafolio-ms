package com.co.indra.coinmarketcap.portafolio.repository;

import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.models.responses.AssetAvgDist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class AssetRowMapper implements RowMapper<Asset> {
    @Override
    public Asset mapRow(ResultSet rs, int rowNum) throws SQLException {
        Asset asset = new Asset();
        asset.setId(rs.getInt("id_assets"));
        asset.setIdPortfolio(rs.getInt("id_portfolio"));
        asset.setAccouting(rs.getInt("accouting"));
        asset.setNameAsset(rs.getString("name_asset"));
        asset.setQuantity(rs.getInt("quantity"));
        asset.setPrice(rs.getDouble("price"));
        asset.setDailyVariation(rs.getDouble("daily_variation"));
        asset.setHolding(rs.getDouble("holding"));
        asset.setAvgBuyPrice(rs.getDouble("avg_buy_price"));
        asset.setProfit(rs.getDouble("profit"));
        asset.setLoss(rs.getDouble("loss"));
        return asset;
    }
}

@Repository
public class AssetRepository {

    @Autowired
    private JdbcTemplate template;

    public void createAsset(@NotNull Asset asset, int idPortfolio) {
        template.update(
                "INSERT INTO tbl_assets(id_portfolio, accouting ,"
                        + "name_asset,quantity,price,daily_variation,holding, avg_buy_price, profit ,"
                        + "loss) values(?,?,?,?,?,?,?,?,?,?)",
                idPortfolio, asset.getAccouting(), asset.getNameAsset(), asset.getQuantity(), asset.getPrice(),
                asset.getDailyVariation(), (asset.getPrice() * asset.getQuantity()), asset.getAvgBuyPrice(), asset.getProfit(),
                asset.getLoss());
    }

    public List<Asset> findByPortfolioIdNameAsset(int idPortfolio, String nameAsset) {
        return template.query("SELECT * FROM tbl_assets WHERE id_portfolio=? and name_asset = ?", new AssetRowMapper(), idPortfolio, nameAsset);
    }
    public List<AssetAvgDist> findAssetsAvgNameByIdPortfolio(int idPortfolio) {
        return template.query("SELECT tbl_assets.name_asset, holding*100/tbl_portfolio.balance_portfolio as avg_distribution FROM tbl_assets" +
                        " INNER JOIN tbl_portfolio ON tbl_assets.id_portfolio  = tbl_portfolio.id_portfolio WHERE tbl_assets.id_portfolio=?",
                (rs, rn) -> new AssetAvgDist(rs.getString("name_asset"),
                        (rs.getDouble("avg_distribution"))), idPortfolio);
    }

    public List<Asset> findById(int idAsset) {
        return template.query("SELECT * FROM tbl_assets WHERE id_assets=?", new AssetRowMapper(), idAsset);
    }

    public int delete(int id) {
        return template.update("DELETE FROM tbl_assets where id_assets=?", id);
    }

    public void modifyAvgBuyPrice(int idAsset, Double amountTotal, int quantityTotal) {
        template.update("UPDATE tbl_assets SET avg_buy_price  = ? WHERE id_assets = ?",
                (amountTotal / quantityTotal), idAsset);
    }

    public void updateAsset(Transaction transaction, int idAsset, Double amountTotal, int quantityTotal, int currentQuantity, Double currentPrice) {
        template.update("UPDATE tbl_assets SET avg_buy_price  = ?, quantity = ?, holding = ? WHERE id_assets = ?",
                (amountTotal / quantityTotal), transaction.getQuantity() + currentQuantity, (transaction.getQuantity() + currentQuantity) * currentPrice, idAsset);
    }

    public List<Asset> findByPortfolioId(int idPortfolio) {
        return template.query("SELECT * FROM tbl_assets WHERE id_portfolio=?", new AssetRowMapper(), idPortfolio);
    }
}
