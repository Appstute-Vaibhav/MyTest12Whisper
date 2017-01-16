/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

// java imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
// spring framework imports
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;







// application imports
import com.whispers.beans.SponsorCard;
import com.whispers.dao.SponsorCardDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain Sponsor Card master table
 */

@Repository
public class SponsorCardDAOImpl extends TransactionService implements SponsorCardDAO {

	/**
	 * Inserts a record in SponsorCard table.
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the SponsorCard 	 {@link SponsorCard }
	 */
	@Override
	public SponsorCard addSponsorCardRecord( SponsorCard sponsorCard ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSponsorCardRecord()...");

		Integer id= -1;

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + SponsorCardMasterSQL.INSERT_SQL);

			sponsorCard.setStatus(1);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sponsorCard);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(SponsorCardMasterSQL.INSERT_SQL, paramSource, generatedKey);

			id = generatedKey.getKey().intValue();
			sponsorCard.setId( id);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSponsorCardRecord(): Record added successfully: " + sponsorCard.toString());

		return sponsorCard;

	} //addSponsorCardRecord() method ends


	/**
	 * Updates record in SponsorCard table.
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the SponsorCard 	 {@link SponsorCard }
	 */
	@Override
	public SponsorCard updateSponsorCardRecord( SponsorCard sponsorCard ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateSponsorCardRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + SponsorCardMasterSQL.UPDATE_SQL);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sponsorCard);
			getNamedParameterJdbcTemplate().update(SponsorCardMasterSQL.UPDATE_SQL, paramSource);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSponsorCardRecord(): Record update suucessfully: " + sponsorCard.toString());

		return sponsorCard;

	} //updateSponsorCardRecord() method ends


	/**
	 * Delete record(s) in SponsorCard table. In case of softdelete, Status is set to 0 
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the deleted record count 	 {@link Integer}
	 */
	@Override
	public Integer deleteSponsorCardRecord( final List<SponsorCard> deleteSponsorCardRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSponsorCardRecord()...");

			Integer rowsAffectedCount = -1;

			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + SponsorCardMasterSQL.DELETE_SQL);

			getJdbcTemplate().batchUpdate(SponsorCardMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					SponsorCard sponsorCard = deleteSponsorCardRecords.get(index);
					pstmt.setInt(1, sponsorCard.getId());
				}

				@Override
				public int getBatchSize() {

					return deleteSponsorCardRecords.size();
				}
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSponsorCardRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteSponsorCardRecord() method ends


	/**
	 * Fetches a single record from SponsorCard table.
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the SponsorCard 	 {@link SponsorCard }
	 */
	@Override
	public SponsorCard fetchSponsorCardRecord(SponsorCard sponsorCard) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSponsorCardRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + SponsorCardMasterSQL.SELECT_SQL);

			sponsorCard = getJdbcTemplate().query( SponsorCardMasterSQL.SELECT_SQL, new Object[] { sponsorCard.getId()}, new ResultSetExtractor<SponsorCard>() {

				SponsorCard sponsorCardTemp = new SponsorCard();

				@Override
				public SponsorCard extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						sponsorCardTemp.setId(rs.getInt("Id"));
						sponsorCardTemp.setSponsoredBy(rs.getString("SponsoredBy"));
						sponsorCardTemp.setCardDescription(rs.getString("CardDescription"));
						sponsorCardTemp.setDateUploaded(rs.getTimestamp("DateUploaded"));
						sponsorCardTemp.setCardImage(rs.getString("CardImage"));
						sponsorCardTemp.setSponsorURI(rs.getString("SponsorURI"));
						sponsorCardTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						sponsorCardTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						sponsorCardTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sponsorCardTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						sponsorCardTemp.setStatus(rs.getInt("Status"));
					}

					return sponsorCardTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSponsorCardRecord(): Record fetched successfully: " + sponsorCard.toString());

		return sponsorCard;

	} //fetchSponsorCardRecord() method ends


	/**
	 * Fetches all records from SponsorCard table.
	 * @param none
	 *
	 * @return 	 Returns the list of SponsorCard 	 {@link List of SponsorCard }
	 */
	@Override
	public List<SponsorCard> fetchAllSponsorCardRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSponsorCardRecord()...");

		List<SponsorCard> sponsorCardList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + SponsorCardMasterSQL.SELECTALL_SQL);

			sponsorCardList = getJdbcTemplate().query( SponsorCardMasterSQL.SELECTALL_SQL, new Object[] { }, new ResultSetExtractor<List<SponsorCard>>() {

				List<SponsorCard> sponsorCardTempList = new ArrayList<SponsorCard>();

				@Override
				public List<SponsorCard> extractData(ResultSet rs) throws SQLException, DataAccessException {

					SponsorCard sponsorCardTemp = null;

					while(rs.next()) {
						sponsorCardTemp = new SponsorCard();

						sponsorCardTemp.setId(rs.getInt("Id"));
						sponsorCardTemp.setSponsoredBy(rs.getString("SponsoredBy"));
						//sponsorCardTemp.setCardDescription(rs.getString("CardDescription"));
						sponsorCardTemp.setDateUploaded(rs.getTimestamp("DateUploaded"));
						//sponsorCardTemp.setCardImage(rs.getString("CardImage"));
						//sponsorCardTemp.setSponsorURI(rs.getString("SponsorURI"));
						//sponsorCardTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						//sponsorCardTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						//sponsorCardTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sponsorCardTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						//sponsorCardTemp.setStatus(rs.getInt("Status"));

						sponsorCardTempList.add(sponsorCardTemp);
					}

					return sponsorCardTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSponsorCardRecord(): All records fetched successfully: " + sponsorCardList.toString());

		return sponsorCardList;

	} //fetchAllSponsorCardRecord() method ends


	/**
	 * Fetches all records for selected criteria from SponsorCard table.
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the list of SponsorCard 	 {@link List<SponsorCard> }
	 */
	@Override
	public List<SponsorCard> fetchSponsorCardRecords( SponsorCard sponsorCard ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSponsorCardRecords()...");

		List<SponsorCard> sponsorCardList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + SponsorCardMasterSQL.SELECTALLCRITERIA_SQL);

			sponsorCardList = getJdbcTemplate().query( SponsorCardMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {
				sponsorCard.getId(), sponsorCard.getId(), sponsorCard.getSponsoredBy(), sponsorCard.getSponsoredBy(), sponsorCard.getCardDescription(), sponsorCard.getCardDescription(), sponsorCard.getDateUploaded(), sponsorCard.getDateUploaded(), sponsorCard.getCardImage(), sponsorCard.getCardImage(), sponsorCard.getSponsorURI(), sponsorCard.getSponsorURI(), sponsorCard.getModifiedOn(), sponsorCard.getModifiedOn(), sponsorCard.getModifiedBy(), sponsorCard.getModifiedBy(), sponsorCard.getCreatedBy(), sponsorCard.getCreatedBy()
				}, new ResultSetExtractor<List<SponsorCard>>() {

				List<SponsorCard> sponsorCardTempList = new ArrayList<SponsorCard>();

				@Override
				public List<SponsorCard> extractData(ResultSet rs) throws SQLException, DataAccessException {

					SponsorCard sponsorCardTemp = null;

					while(rs.next()) {
						sponsorCardTemp = new SponsorCard();

						sponsorCardTemp.setId(rs.getInt("Id"));
						sponsorCardTemp.setSponsoredBy(rs.getString("SponsoredBy"));
						sponsorCardTemp.setCardDescription(rs.getString("CardDescription"));
						sponsorCardTemp.setDateUploaded(rs.getTimestamp("DateUploaded"));
						sponsorCardTemp.setCardImage(rs.getString("CardImage"));
						sponsorCardTemp.setSponsorURI(rs.getString("SponsorURI"));
						sponsorCardTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						sponsorCardTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						sponsorCardTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sponsorCardTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						sponsorCardTemp.setStatus(rs.getInt("Status"));

						sponsorCardTempList.add(sponsorCardTemp);
					}

					return sponsorCardTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSponsorCardRecords(): All records fetched successfully: " +sponsorCardList.toString());

		return sponsorCardList;

	} //fetchSponsorCardRecords() method ends


}