/*******************************************************************************
 * Copyright (c) 2017-2018 Aion foundation.
 *
 *     This file is part of the aion network project.
 *
 *     The aion network project is free software: you can redistribute it
 *     and/or modify it under the terms of the GNU General Public License
 *     as published by the Free Software Foundation, either version 3 of
 *     the License, or any later version.
 *
 *     The aion network project is distributed in the hope that it will
 *     be useful, but WITHOUT ANY WARRANTY; without even the implied
 *     warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *     See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with the aion network project source files.
 *     If not, see <https://www.gnu.org/licenses/>.
 *
 *
 * Contributors:
 *     Aion foundation.

 ******************************************************************************/
package org.aion.mcf.valid;

import org.aion.base.type.IBlockHeader;
import org.aion.mcf.blockchain.valid.IBlockHeaderValidRule;

import java.util.List;

/**
 * validation rules depending on parent's block header
 */
public class ParentBlockHeaderValidator<BH extends IBlockHeader> extends DependentBlockHeaderRule<BH> {

    private List<DependentBlockHeaderRule<BH>> rules;

    public ParentBlockHeaderValidator(List<DependentBlockHeaderRule<BH>> rules) {
        this.rules = rules;
    }

    @Override
    public boolean validate(BH header, BH parent) {
        errors.clear();

        for (IBlockHeaderValidRule<BH> rule : rules) {
            if (!(rule).validate(header, parent)) {
                errors.addAll(rule.getErrors());
                return false;
            }
        }

        return true;
    }
}
